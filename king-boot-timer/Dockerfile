FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} king-boot-timer.jar
ENTRYPOINT ["java","-jar","/king-boot-timer.jar"]