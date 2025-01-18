FROM maven:3.9.7-amazoncorretto-17 AS build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean install -y

FROM maven:3.8-openjdk-17

COPY --from=build /app/target/course-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app-jar" ]