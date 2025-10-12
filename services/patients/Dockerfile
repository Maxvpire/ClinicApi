# -------- BUILD STAGE --------
FROM maven:3.9.6-eclipse-temurin-23 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (better caching)
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# -------- RUNTIME STAGE --------
FROM eclipse-temurin:24-jre
WORKDIR /app

# Copy only the final jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose application port (change if needed)
EXPOSE 7788

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]