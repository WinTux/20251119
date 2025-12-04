# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jdk
RUN groupadd -r appgroup && useradd -r -g appgroup appuser
WORKDIR /app
COPY target/orden-service-0.0.1-SNAPSHOT.jar app.jar
RUN chown -R appuser:appgroup /app
EXPOSE 8080 8081 8082
USER appuser
ENTRYPOINT ["java", "-jar", "app.jar"]
