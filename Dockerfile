FROM openjdk:8
EXPOSE 8080
WORKDIR /app
ADD target/myproject.jar myproject.jar
CMD ["java", "-jar", "myproject.jar"]