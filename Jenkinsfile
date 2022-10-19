pipeline {
    agent {label 'docker'}
    stages {
       stage ('Docker Build')
        when {
            anyOf {
                branch 'dev*';
                branch 'master*'
            }
        }
        steps {
            script {
                WEATHER_FORECAST_IMAGE = "jenkins_${BRANCH_NAME}"
            }
        }

    }
}

