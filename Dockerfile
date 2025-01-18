FROM maven:3.9.7-amazoncorretto-17 AS build

RUN mvn clean install

FROM amazoncorretto:17-alpine-jdk

COPY --from=build /app/target/course-0.0.1-SNAPSHOT.jar app.jar

WORKDIR /target

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]