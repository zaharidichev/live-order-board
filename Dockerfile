FROM java:8
MAINTAINER Zahari Dichev <zaharidichev@gmail.com>
EXPOSE 8080
VOLUME /tmp
ADD target/live-order-board-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]