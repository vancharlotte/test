FROM openjdk:8
ADD target/library-loan.jar library-loan.jar
EXPOSE 9002

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait
CMD /wait
ENTRYPOINT ["java", "-jar", "library-loan.jar"]
