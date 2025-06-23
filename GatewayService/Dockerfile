FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY ./GatewayService/pom.xml .

COPY  ./GatewayService/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar gatewayservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "gatewayservice.jar"]
