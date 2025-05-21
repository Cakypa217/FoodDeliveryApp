FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/orderService-1.0-SNAPSHOT.jar app.jar
EXPOSE 8088
CMD ["java", "-jar", "app.jar"]