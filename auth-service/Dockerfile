# Use an OpenJDK base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/auth-service-*.jar app.jar

# Expose the port
EXPOSE 9002

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
