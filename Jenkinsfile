
pipeline{
    agent any
    stages {
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

