FROM openjdk:8-jdk
COPY target/translate.jar translate.jar
CMD ["java", "-jar", "translate.jar"]