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
                    -t ispace:latest .
                '''
            }
        }
        stage("deploy") {
            steps {
                echo "deploying the application"
            }
        }
    }
}
