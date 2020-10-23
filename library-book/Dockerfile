FROM openjdk:8
ADD target/library-book.jar library-book.jar
EXPOSE 9001

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait
CMD /wait
ENTRYPOINT ["java", "-jar", "library-book.jar"]