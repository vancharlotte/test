FROM openjdk:8
ADD target/zuul-server.jar zuul-server.jar
EXPOSE 9004

ENTRYPOINT ["java", "-jar", "zuul-server.jar"]
