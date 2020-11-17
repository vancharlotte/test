# Build stage
FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /zuul-server
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:8-jdk-alpine
COPY --from=build /target/zuul-server.jar /zuul-server.jar
EXPOSE 9004
ENTRYPOINT ["java", "-jar", "zuul-server.jar"]
