# Build stage
FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /auth-server
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:8-jdk-alpine
COPY --from=build /target/auth-server.jar /auth-server.jar
EXPOSE 9191
ENTRYPOINT ["java", "-jar", "/auth-server.jar"]