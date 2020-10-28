FROM openjdk:8
ADD target/client-ui.jar client-ui.jar
EXPOSE 8000

ENTRYPOINT ["java", "-jar", "client-ui.jar"]