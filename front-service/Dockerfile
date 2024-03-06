FROM openjdk:17-alpine
EXPOSE 3000
ARG JAR_FILE=target/front-service-2.6.6.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]