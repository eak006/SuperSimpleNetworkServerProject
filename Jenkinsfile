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
                //sh 'docker rm $(docker stop [$(docker ps -a -q --filter ancestor=[$(docker images -f "dangling=true" -q)])])'
                //sh 'docker rmi $(docker images -f "dangling=true" -q)'
                containers = sh 'docker ps -a -q --filter ancestor=simpleserver:1 --format="{{.ID}}"'
                stopped = sh 'docker stop $containers'
                sh 'docker rm $stopped'
                //sh 'docker rm $(docker stop $(docker ps -a -q --filter ancestor=simpleserver:1 --format="{{.ID}}"))'
                //sh 'docker image prune -f'
                sh 'docker run -u root --rm -d -p 8081:8081 -p 50001:50001 -v /var/run/docker.sock:/var/run/docker.sock simpleserver:1'
            }
        }
    }
}
