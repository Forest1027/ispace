pipeline {
    agent any
    environment {
        PROFILE = "${BRANCH_NAME=='main'?'prod':'dev'}"
    }
    tools {
        maven 'apache-maven-3.8.2'
    }
    stages {
        stage("test") {
            steps {
                sh "mvn clean"
                sh "mvn verify"
            }
        }
        stage("build") {
            steps {
                sh "mvn clean package"
            }
        }
        stage("dockerize") {
            environment {
                DB_USERNAME = credentials('db_username')
                DB_PASSWORD = credentials('db_password')
                OKTA_APIKEY = credentials('okta_apikey')
           }
            steps {
                echo "dockerize the application"
                sh '''
                    docker build  \
                    --build-arg profile=${PROFILE}  \
                    --build-arg db_username=${DB_USERNAME}  \
                    --build-arg db_password=${DB_PASSWORD}  \
                    --build-arg okta_apikey=${OKTA_APIKEY}  \
                    -t 896850397919.dkr.ecr.us-east-1.amazonaws.com/ispace:latest .
                '''
                script {
                    docker.withRegistry('https://896850397919.dkr.ecr.eu-west-1.amazonaws.com', 'ecr:ispace_aws') {
                        //push image
                        sh "docker push 896850397919.dkr.ecr.us-east-1.amazonaws.com/ispace:latest"
                    }
                }

            }
        }
        stage("deploy") {
            steps {
                echo "deploying the application"
                sh "docker stop ispace || true && docker rm ispace || true"
                sh "docker run -d --network=\'host\' --name ispace 896850397919.dkr.ecr.us-east-1.amazonaws.com/ispace:latest"
            }
        }
    }
}
