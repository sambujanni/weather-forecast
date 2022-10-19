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

