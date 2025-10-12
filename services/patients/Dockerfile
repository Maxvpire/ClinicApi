# Stage 1: Build the application
# Use a Java JDK image for building the application.
FROM sapmachine:24-jdk-ubuntu-noble AS builder

# Set the working directory for the build stage
WORKDIR /app

# Copy the Maven wrapper files and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Grant execute permission to the Maven wrapper script
RUN chmod +x ./mvnw

# Download dependencies to leverage Docker's build cache
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY src src

# Package the application into a JAR file
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final, smaller runtime image
# Use a JRE-only image for a smaller and more secure production image.
FROM sapmachine:24-jre-ubuntu-noble AS runtime

# Set the working directory for the runtime stage
WORKDIR /app

# Create a non-root user for security best practices.
RUN adduser --system --group spring
USER spring

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 7788

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
