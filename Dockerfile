FROM maven:4.0.0-amazoncorretto-17 AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY . .

RUN mvn clean install

FROM amazoncorretto:17-alpine-jdk

EXPOSE 8080

COPY --from=build /target/course-0.0.1-SNAPSHOT app.jar



ENTRYPOINT [ "java", "-jar", "app.jar"]