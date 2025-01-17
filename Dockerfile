FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-11-jdk -y
COPY . .

RUN apt-cache search maven

RUN apt-get update && apt-get install maven -y
RUN mvn -version
RUN mvn clean install

FROM openjdk:11-jdk-slim

EXPOSE 8080

COPY --from=build /target/course.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar"]