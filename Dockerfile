# Use a base image with JDK 17 for building the app
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy only the required files for dependency resolution
COPY pom.xml ./

# Download dependencies first (cache optimization)
RUN mvn dependency:go-offline

# Copy the source code separately (avoiding unnecessary files)
COPY src ./src

# Run Maven build to create the JAR file
RUN mvn clean package -DskipTests && ls -l target

# Use a lightweight JDK image for running the application
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy only the generated JAR file from the build stage
COPY --from=build /app/target/rate-my-college-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Set environment variables (customize as needed)
ENV DATASOURCE_URI=mongodb+srv://nishantupmanyu24:b0igQmmoLLdc2Tdc@cluster0.wvt73.mongodb.net/RateMyCollege?retryWrites=true&w=majority
ENV FRONTEND_URL=http://localhost:3000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
