# Use an OpenJDK base image
FROM openjdk:23-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/user-service-*.jar app.jar

# Expose the port
EXPOSE 9000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
