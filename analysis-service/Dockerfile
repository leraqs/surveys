FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/analysis-service-0.0.1-SNAPSHOT.jar analysis-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "analysis-service.jar"]