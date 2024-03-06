FROM openjdk:17-alpine
EXPOSE 8192
ARG JAR_FILE=target/core-service-2.6.6.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]