# Stage 1: Build the application
FROM sapmachine:24-jdk-ubuntu-noble AS builder
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests -e -X


# *** ADD THESE DEBUGGING LINES ***
RUN echo "Contents of /app:" && ls -R /app
# ********************************

# Stage 2: Create the final, smaller runtime image
FROM sapmachine:24-jre-ubuntu-noble AS runtime
WORKDIR /app
RUN adduser --system --group spring
USER spring
EXPOSE 7766
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
