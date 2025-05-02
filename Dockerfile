# Use an official OpenJDK 17 image as base
FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/Car_Rental_Backend-0.0.1-SNAPSHOT.jar
# Set the working directory
WORKDIR /app

# Copy your Java app JAR into the container
COPY ${JAR_FILE} spring-boot-carrental.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "spring-boot-carrental.jar"]
