FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "notification-service.jar"]