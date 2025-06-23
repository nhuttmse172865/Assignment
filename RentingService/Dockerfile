FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY ./RentingService/pom.xml .

COPY ./RentingService/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar rentingservice.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "rentingservice.jar"]
