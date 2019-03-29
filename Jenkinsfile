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
            }
        }
        stage('Deploy') {
            steps {
                echo '----DEPLOYING TO DOCKER----'
                sh 'docker build . -t simpleserver:1'

                script {
                    try {
                        // Find containers using old image
                        def containers = sh(returnStdout: true, script: 'docker ps -a -q --filter ancestor=simpleserver:1 --format="{{.ID}}"')
                        echo "Removing containers: ${containers}"
                        // Stop old containers
                        def stopped = sh(returnStdout: true, script: "docker stop ${containers}")
                        // Remove old containers
                        sh(returnStdout: false, script: "docker rm ${stopped}")
                    } catch (exc) {
                        // Catch exception thrown when no containers found
                    }
                }

                // Run new container
                sh 'docker run -u root --rm -d -p 8081:8081 -p 50001:50001 -v /var/run/docker.sock:/var/run/docker.sock -v /simpleserver:/files simpleserver:1'
            }
        }
    }
}
