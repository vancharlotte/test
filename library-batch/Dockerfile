FROM openjdk:8
ADD target/library-batch.jar library-batch.jar
EXPOSE 9005
ENTRYPOINT ["java", "-jar", "library-batch.jar"]