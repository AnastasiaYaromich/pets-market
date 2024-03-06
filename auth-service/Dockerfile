FROM openjdk:17-alpine
EXPOSE 8194
ARG JAR_FILE=target/auth-service-2.6.6.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
