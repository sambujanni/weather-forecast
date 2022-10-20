pipeline {
    agent any
//         tools {
//             maven 'MAVEN_HOME'
//         }
        environment {
            REPO_NAME = "weather-forecast"
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

                        // echo "commit id:: ${COMMIT_ID}"
                        WEATHER_FORECAST_IMAGE = "jenkins_${BRANCH_NAME}_b${env.BUILD_ID}_${COMMIT_ID}"
                        echo "BRANCH NAME: ${env.BRANCH_NAME}"
                        bat 'mvn clean install'
                        bat "docker build . -t ${REPO_NAME}:${WEATHER_FORECAST_IMAGE}"
                    }

                }
            }
        }
}