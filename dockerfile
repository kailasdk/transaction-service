# Use official OpenJDK image
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY target/transaction-service-*.jar transaction-service.jar

# Expose the port
EXPOSE 8082

# Run the app
ENTRYPOINT ["java", "-jar", "transaction-service.jar"]
