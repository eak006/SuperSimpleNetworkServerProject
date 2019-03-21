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
                docker 'build . -t simpleserver:1'

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
                
            }
        }
    }
}
