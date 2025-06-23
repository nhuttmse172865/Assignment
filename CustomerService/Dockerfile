FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

COPY ./CustomerService/pom.xml .

COPY ./CustomerService/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar customerservice.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "customerservice.jar"]
