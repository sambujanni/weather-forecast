
pipeline{
    agent any
    tools {
        maven 'MAVEN'
    }
    stages {
        stage('Build Maven') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: '0d327d79-9fba-4981-8fcf-8ae1094d4dcc', url: 'https://github.com/sambujanni/weather-forecast.git']]])
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

