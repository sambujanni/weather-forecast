pipeline {
    agent {label 'docker'}
    stages {
       stage ('Docker Build')
        steps {
            script {
                WEATHER_FORECAST_IMAGE = "jenkins_${BRANCH_NAME}"
            }
        }

    }
}

pipeline{
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Build Maven') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'devopshint', url: 'https://github.com/devopshint/jenkins...]]])

                sh "mvn -Dmaven.test.failure.ignore=true clean package"

            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                  sh 'docker build -t jenkins_weather-forecast .'
                }
            }
        }
        stage('Deploy Docker Image') {
            steps {
                script {
                 withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u sambujanni -p July@1990'
                 }
                 sh 'docker push jenkins_weather-forecast'
                }
            }
        }
    }
}

