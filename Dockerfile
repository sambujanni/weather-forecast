FROM openjdk
COPY target/*.jar /
EXPOSE 8080
ENTRYPOINT ["java","-jar","/weather-forecast-0.0.1-SNAPSHOT.jar"]