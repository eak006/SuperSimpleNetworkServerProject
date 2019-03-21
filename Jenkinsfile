pipeline {
    agent {
        docker {
            image 'maven:latest'
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
                sh 'mvn -B clean package'
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
