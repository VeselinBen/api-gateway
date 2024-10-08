# Step 1: Use Maven to build the application
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Run Maven clean package to build the application
RUN mvn clean package -DskipTests

# Step 2: Use OpenJDK to run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/gateway-service-0.0.1-SNAPSHOT.jar /app/gateway-service.jar

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "gateway-service.jar"]
