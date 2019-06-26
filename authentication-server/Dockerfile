FROM openjdk:11

ADD ./target/authentication-server-0.0.1-SNAPSHOT.jar /usr/src/authentication-server-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "authentication-server-0.0.1-SNAPSHOT.jar"]