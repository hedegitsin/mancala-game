FROM maven:3.9.5-eclipse-temurin-17 as build-stage
WORKDIR /home/app
COPY ./src ./src
COPY ./pom.xml .
RUN mvn clean package

FROM eclipse-temurin:17-jdk
COPY --from=build-stage /home/app/target/mancala-api-0.0.1-SNAPSHOT.jar /home/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
