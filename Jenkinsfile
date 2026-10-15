pipeline {
    agent any

    tools {
        jdk 'JDK21'
        nodejs 'Node24'
    }

    stages {
        stage('Backend - Build and Test') {
            steps {
                dir('backend') {
                    sh 'chmod +x mvnw'
                    sh './mvnw clean verify'
                }
            }
        }

        stage('Frontend - Install and Build') {
            steps {
                dir('frontend') {
                    sh 'npm ci'
                    sh 'npm run build'
                }
            }
        }
    }

    post {
        success {
            echo 'FraudWatch CI pipeline completed successfully.'
        }
        failure {
            echo 'FraudWatch CI pipeline failed.'
        }
    }
}
