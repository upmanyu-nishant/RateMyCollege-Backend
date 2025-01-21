# Use a base image with JDK 17
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the application JAR file into the container
ARG JAR_FILE=target/rate-my-college-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose the port on which the application runs
EXPOSE 8080

# Set environment variables for MongoDB connection
ENV DATASOURCE_URI=mongodb+srv://nishantupmanyu24:b0igQmmoLLdc2Tdc@cluster0.wvt73.mongodb.net/RateMyCollege?retryWrites=true&w=majority
ENV FRONTEND_URL=http://localhost:3000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
