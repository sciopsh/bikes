FROM openjdk:11
FROM maven:latest
WORKDIR /app
COPY . /app
RUN mvn -v
RUN mvn clean install -DskipTests

