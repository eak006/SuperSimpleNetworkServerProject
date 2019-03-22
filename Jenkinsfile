pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                echo '----BUILDING----'
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                echo '----TESTING----'
                sh 'mvn test'
            }
            post {
                always {
                    echo '----POST TEST----'
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                echo '----DELIVERING----'
                script{
                    def customImage = docker.build("simpleserver:1")
                    customImage.withRun('-u root --rm -d -p 8081:8081 -p 50001:50001 -v /var/run/docker.sock:/var/run/docker.sock')
                }
            }
        }
    }
}
//node {
//    sh 'docker build . -t simpleserver:1'
//    sh 'docker run -u root --rm -d -p 8081:8081 -p 50001:50001 -v /var/run/docker.sock:/var/run/docker.sock simpleserver:1'
//}
