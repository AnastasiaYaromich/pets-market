FROM openjdk:17-alpine
EXPOSE 8193
ARG JAR_FILE=target/cart-service-2.6.6.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]