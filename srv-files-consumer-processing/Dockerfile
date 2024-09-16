FROM openjdk:17-alpine

COPY ./target/srv-files-consumer-processing-*.jar /app/main.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "main.jar"]