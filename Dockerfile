# FROM ubuntu:latest As build
# RUN apt-get update
# RUN apt-get install openjdk-21-jdk -y
# RUN apt-get install maven -y
# COPY . .
# RUN mvn clean install
# FROM openjdk:21
# COPY --from=build /ecommerce-backend-java.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]
# EXPOSE 8080
FROM maven:3.8.5-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/backend-0.0.1-SNAPSHOT.jar backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","backend.jar"]