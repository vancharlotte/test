FROM openjdk:8
ADD target/config-server.jar config-server.jar
EXPOSE 9101
ENTRYPOINT ["java", "-jar", "config-server.jar"]