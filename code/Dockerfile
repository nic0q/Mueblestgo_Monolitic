FROM openjdk:18
ARG JAR_FILE=target/MyWebApp1.jar
COPY ${JAR_FILE} MyWebApp1.jar
ENTRYPOINT ["java","-jar","/MyWebApp1.jar"]