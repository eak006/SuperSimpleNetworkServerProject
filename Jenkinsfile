pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Test'){
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                }
            }
            stages {
                stage('Initialize'){
                    steps{
                        echo '----INITIALIZING DOCKER----'
                    }
                }
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
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build . -t simpleserver:1'
                try {
                    sh 'docker rmi $(docker images -f "dangling=true" -q)'
                } catch (Exception e) {
                    //This will throw errors sometimes
                }
                sh 'docker run -u root --rm -d -p 8081:8081 -p 50001:50001 -v /var/run/docker.sock:/var/run/docker.sock simpleserver:1'

            }
        }
    }
}
