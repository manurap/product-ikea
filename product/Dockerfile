# Use an official Java runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built application JAR into the container
COPY target/product-0.0.1-SNAPSHOT.jar product-service.jar

# Expose the application's port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "product-service.jar"]