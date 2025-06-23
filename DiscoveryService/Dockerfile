FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY ./DiscoveryService/pom.xml .

COPY  ./DiscoveryService/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar discoveryservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "discoveryservice.jar"]
