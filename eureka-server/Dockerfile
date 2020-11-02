# Build stage
FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /eureka-server
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:8-jdk-alpine
COPY --from=build /target/eureka-server.jar /eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]