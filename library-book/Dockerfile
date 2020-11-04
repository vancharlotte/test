# Build stage
FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /library-book
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean install

# Run stage
FROM openjdk:8-jdk-alpine
COPY --from=build /target/library-book.jar /library-book.jar
EXPOSE 9003
ENTRYPOINT ["java", "-jar", "/library-book.jar"]