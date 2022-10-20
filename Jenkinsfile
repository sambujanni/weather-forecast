node {
    // reference to maven
    // ** NOTE: This 'maven-3.6.1' Maven tool must be configured in the Jenkins Global Configuration.   
    def mvnHome = tool 'MAVEN_HOME'

    // holds reference to docker image
    def dockerImage
    // ip address of the docker private repository(nexus)
    
    def dockerRepoUrl = "https://hub.docker.com/repository/docker/sivajanni/weather-forecast"
    def dockerImageName = "weather-forecast"
    def dockerImageTag = "${dockerRepoUrl}/${dockerImageName}:${env.BUILD_NUMBER}"
    
    stage('Clone Repo') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/sambujanni/weather-forecast.git'
      // Get the Maven tool.
      // ** NOTE: This 'maven-3.6.1' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'MAVEN_HOME'
    }    
  
    stage('Build Project') {
      // build project via maven
      sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
    }
	
// 	stage('Publish Tests Results'){
//       parallel(
//         publishJunitTestsResultsToJenkins: {
//           echo "Publish junit Tests Results"
// 		  archive 'target/*.jar'
//         },
//        )
//     }
		
    stage('Build Docker Image') {
      // build docker image
      sh "whoami"
      sh "ls -all /var/run/docker.sock"
      sh "mv ./target/weather-forecast-0.0.1-SNAPSHOT*.jar ./data"
      
      dockerImage = docker.build("weather-forecast")
    }
   
    stage('Deploy Docker Image'){
      
      // deploy docker image to nexus

      echo "Docker Image Tag Name: ${dockerImageTag}"

      sh "docker login -u sivajanni -p July@1990 ${dockerRepoUrl}"
      sh "docker tag ${dockerImageName} ${dockerImageTag}"
      sh "docker push ${dockerImageTag}"
    }
}