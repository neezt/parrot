FROM openjdk:11 as build

EXPOSE 8080

ADD target/parrot-example.jar parrot-example.jar

ENTRYPOINT ["java","-jar","parrot-example.jar"]