FROM openjdk:17-alpine

COPY ./target/srv-files-consumer-notify-*.jar /app/main.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "main.jar"]