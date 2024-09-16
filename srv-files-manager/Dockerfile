FROM openjdk:17-alpine

COPY ./target/srv-files-manager-*.jar /app/main.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "main.jar"]