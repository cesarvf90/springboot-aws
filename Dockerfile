FROM openjdk:17-jdk-slim-buster
MAINTAINER springbootdata.training.com
COPY target/springboot-data-1.0.0.jar springboot-data-1.0.0.jar
ENTRYPOINT ["java","-jar","/springboot-data-1.0.0.jar"]