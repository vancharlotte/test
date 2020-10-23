FROM openjdk:8
ADD target/zuul-server.jar zuul-server.jar
EXPOSE 9004

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait
CMD /wait
ENTRYPOINT ["java", "-jar", "zuul-server.jar"]
