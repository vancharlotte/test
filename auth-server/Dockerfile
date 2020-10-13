FROM openjdk:8
ADD target/auth-server.jar auth-server.jar
EXPOSE 9191

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait
CMD /wait
ENTRYPOINT ["java", "-jar", "auth-server.jar"]