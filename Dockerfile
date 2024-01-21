FROM openjdk:17-jdk

ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} api.jar

RUN echo JAR_FILE

ENTRYPOINT ["java", "-Xms1024M", "-Xmx1024M", "-jar", "-Dspring.profiles.active=prod", "api.jar"]
