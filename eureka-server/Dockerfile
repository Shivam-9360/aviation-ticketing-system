# Use an OpenJDK base image
FROM openjdk:23-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/eureka-server-*.jar app.jar

# Expose the port
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
