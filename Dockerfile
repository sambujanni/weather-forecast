FROM openjdk:11
EXPOSE 8080
ADD target/weather-forecast-0.0.1-SNAPSHOT.jar weather-forecast-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/weather-forecast-0.0.1-SNAPSHOT.jar"]