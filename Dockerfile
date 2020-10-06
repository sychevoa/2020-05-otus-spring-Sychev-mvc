FROM openjdk:11-jre-slim
COPY /target/2020-05-otus-spring.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
