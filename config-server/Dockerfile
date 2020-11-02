# Build stage
FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /config-server
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:8-jdk-alpine
COPY --from=build /target/config-server.jar /config-server.jar
EXPOSE 9101
ENTRYPOINT ["java", "-jar", "/config-server.jar"]