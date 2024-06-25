FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/survey-service-0.0.1-SNAPSHOT.jar survey-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "survey-service.jar"]