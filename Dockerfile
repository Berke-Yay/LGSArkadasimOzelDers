FROM openjdk:17

RUN mkdir -p /home/app

COPY . /home/app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]