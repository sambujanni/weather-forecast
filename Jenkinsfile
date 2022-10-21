pipeline {
    agent any
        tools {
            maven 'MAVEN_HOME'
        }
        environment {
            REPO_NAME = "sivajanni/weather-forecast"
            COMMIT_ID = bat(script: "git rev-parse HEAD", returnStdout: true).trim().readLines().drop(1).join(" ")
        }
        stages {
            stage('Build Docker Image') {
                when {
                    anyOf {
                        branch 'development*';
                        branch 'master*';
                    }
                }
                steps {
                    script {
                        WEATHER_FORECAST_IMAGE = "jenkins_${BRANCH_NAME}_b${env.BUILD_ID}_${COMMIT_ID}"
                        bat 'mvn clean install'
                        bat "docker build . -t ${REPO_NAME}:${WEATHER_FORECAST_IMAGE}"

                    }

                }
            }
            stage('Push to DockerHub') {
                steps {
                    script {
                        bat "docker login -u sivajanni -p July@1990"
                        bat "docker push sivajanni/weather-forecast:${WEATHER_FORECAST_IMAGE}"
				    }
                }
            }
            stage('Deploy') {
                steps {
                    script {
                        bat "docker run -d --name weather-forecast -p 8080:8080 ${REPO_NAME}:${WEATHER_FORECAST_IMAGE}"
                    }
                }
            }
        }
    }
