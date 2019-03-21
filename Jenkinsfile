pipeline {
    agent {
        sh 'docker build . -t simplerserver:1'
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
                
            }
        }
    }
}
