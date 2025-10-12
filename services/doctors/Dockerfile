# Use a base image with OpenJDK 24
FROM openjdk:24-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose the port your Spring Boot application listens on (default is 8080)
EXPOSE 7799

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]