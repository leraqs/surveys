FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY build/libs/payment-service-0.0.1-SNAPSHOT.jar payment-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "payment-service.jar"]