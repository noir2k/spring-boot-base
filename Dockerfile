FROM openjdk:8
VOLUME /tmp
ARG JAR_FILE=./build/docker/colligence-api-1.0.0.jar
COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker-build","-jar","/app.jar"]