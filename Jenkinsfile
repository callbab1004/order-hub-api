pipeline {
    agent any

    environment {
        IMAGE_NAME = "order-hub-api"
        IMAGE_TAG  = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh "./gradlew clean test"
            }
        }

        stage('BootJar') {
            steps {
                sh "./gradlew bootJar"
            }
        }

        stage('Docker Build') {
            steps {
                sh """
                    docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                """
            }
        }

        // 나중에 레지스트리 쓰면 여기서 push 추가 예정
        // stage('Docker Push') { ... }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
        }
    }
}
