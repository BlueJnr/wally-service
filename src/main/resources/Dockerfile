FROM amazoncorretto:17-alpine

ENV LANG en_US.UTF-8

EXPOSE 8080

RUN mkdir /app

ADD target/ROOT.jar /app

CMD ["java", "-jar", "/app/ROOT.jar"]