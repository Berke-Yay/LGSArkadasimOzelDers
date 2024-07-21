FROM openjdk:17

ENV db_password="178529"
ENV db_username="postgres"
ENV db_url="jdbc:postgresql://localhost:5432/ozelders"
ENV mail_password="buvovzvsbnzbdpsb"
ENV mail_username="lgsarkadasimprojesi@gmail.com"

RUN mkdir -p /home/app

COPY . /home/app

EXPOSE 8080

CMD ["java", "-jar", "/home/app/target/LGSArkadasimOzelDers-0.0.1-SNAPSHOT.jar"]