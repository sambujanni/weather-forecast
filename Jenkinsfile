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
                COMMIT_ID = sh(returnStdout: true. script: 'git rev-parse HEAD')
                WEATHER_FORECAST_IMAGE = "jenkins_${BRANCH_NAME}_${COMMIT_ID}"
            }
        }

    }
}

