pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'stefanpyordanov/book-library-app:v2'
        DOCKER_CREDENTIALS_ID = 'docker-hub-creds'
        GIT_REPO = 'https://github.com/StefanPYordanov/BookLibraryConsoleVersion.git'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git url: "${env.GIT_REPO}"
            }
        }

        stage('Build JAR with Maven') {
            steps {
                bat 'mvn clean package assembly:single'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build(env.DOCKER_IMAGE)
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', env.DOCKER_CREDENTIALS_ID) {
                        docker.image(env.DOCKER_IMAGE).push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                bat """
                echo Deploying to Kubernetes...
                kubectl set image deployment/library-app library-app=${DOCKER_IMAGE} --namespace=default
                kubectl rollout status deployment/library-app --namespace=default
                """
            }
        }
    }
}
