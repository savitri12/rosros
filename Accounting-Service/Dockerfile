FROM openjdk:11.0.3-jre-slim-stretch
VOLUME /config
ADD target/AccountingService-0.0.1-SNAPSHOT.jar /AccountingService.jar
EXPOSE 8092
RUN bash -c 'touch /AccountingService.jar'
ENTRYPOINT ["java","-jar","/AccountingService.jar"]
