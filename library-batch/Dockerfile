# Build stage
FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /library-batch
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:8-jdk-alpine
COPY --from=build /target/library-batch.jar /library-batch.jar
EXPOSE 9005
ENTRYPOINT ["java", "-jar", "/library-batch.jar"]