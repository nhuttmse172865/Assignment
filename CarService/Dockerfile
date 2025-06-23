FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY ./CarService/pom.xml .

COPY ./CarService/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar carservice.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "carservice.jar"]
