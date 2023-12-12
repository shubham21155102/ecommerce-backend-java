#FROM openjdk:21
#ADD target/app.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
# EXPOSE 8080
FROM ubuntu:latest As build
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
RUN apt-get install maven -y
COPY . .
RUN mvn clean install
FROM openjdk:21
COPY --from=build /target/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080




