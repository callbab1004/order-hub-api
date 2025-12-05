pipeline {
    agent any

    environment {
        IMAGE_NAME = "order-hub-api"
        IMAGE_TAG  = "${env.BUILD_NUMBER}"
        DOCKER_REPO = "shp93/order-hub-api"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // 만약 H2 프로파일 쓰게 해놨으면:
                sh "SPRING_PROFILES_ACTIVE=test ./gradlew clean test"
            }
        }

        stage('BootJar') {
            steps {
                sh "./gradlew bootJar -x test"
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: '7cc0e225-39a1-40ab-a88b-02f617894164',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh """
                      echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                      docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_REPO}:${IMAGE_TAG}
                      docker push ${DOCKER_REPO}:${IMAGE_TAG}
                    """
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
        }
    }
}
