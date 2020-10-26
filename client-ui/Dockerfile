FROM openjdk:8
ADD target/client-ui.jar client-ui.jar
EXPOSE 8000

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait
CMD /wait
ENTRYPOINT ["java", "-jar", "client-ui.jar"]