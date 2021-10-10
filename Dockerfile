FROM maven:3.6.3-adoptopenjdk-11 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/assignment-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java","-jar", "assignment-0.0.1-SNAPSHOT.jar"]