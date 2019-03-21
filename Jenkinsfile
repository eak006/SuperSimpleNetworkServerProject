pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
        }
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
                echo 'Build'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
                echo 'Test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    echo 'Post test'
                }
            }
        }
        stage('Deliver') { 
            steps {
                sh './jenkins/scripts/deliver.sh'
                echo 'Deliver'
            }
        }
    }
}
