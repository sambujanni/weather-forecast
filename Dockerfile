# Maven build container 

FROM maven:3.8.6-openjdk-11 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk

#maintainer 
MAINTAINER siva.janni11@gmail.com
#expose port 8080
EXPOSE 8080

#default command
CMD java -jar /data/weather-forecast-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /target/weather-forecast-0.0.1-SNAPSHOT.jar /data/weather-forecast-0.0.1-SNAPSHOT.jar