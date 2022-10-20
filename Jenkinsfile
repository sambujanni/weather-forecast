pipeline {
    agent any
        tools {
            maven 'MAVEN_HOME'
        }
        environment {
            REPO_NAME = "weather-forecast"
            COMMIT_ID = bat(script: "git rev-parse HEAD", returnStdout: true).trim().readLines().drop(1).join(" ")


        }
        stages {
            stage('Build Maven') {
                steps {
                    // checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sambujanni/weather-forecast']]])
                    bat 'mvn clean install'
                }
            }
            stage('Build Docker Image') {
                // when {
                //     anyOf {
                //         branch 'development';
                //         branch 'master'
                //     }
                // }
                steps {
                    script {

                        // echo "commit id:: ${COMMIT_ID}"
                        WEATHER_FORECAST_IMAGE = "jenkins-${env.BUILD_ID}_${REPO_NAME}_${COMMIT_ID}"
                        echo "BRANCH NAME: ${env.BRANCH_NAME}"

                        bat "docker build . -t ${WEATHER_FORECAST_IMAGE}"
                    }

                }
            }
        }
}