FROM openjdk:8
ADD target/library-batch.jar library-batch.jar
EXPOSE 9005

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait
CMD /wait
ENTRYPOINT ["java", "-jar", "library-batch.jar"]