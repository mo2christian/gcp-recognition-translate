FROM openjdk:8-jdk
COPY target/translate.jar translate.jar
EXPOSE 8080
CMD ["java", "-jar", "translate.jar"]