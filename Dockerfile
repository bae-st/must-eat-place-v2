FROM openjdk:17-jdk

ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} api.jar

RUN echo JAR_FILE

ARG PROFILE
ENV PROFILE_ENV=${PROFILE}

ENTRYPOINT ["java", "-Xms1024M", "-Xmx1024M", "-jar", "api.jar"]
