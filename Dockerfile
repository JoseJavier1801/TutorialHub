FROM maven:3.8.5-openjdk-17 as build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /target/TutorialHub-1.0-SNAPSHOT.jar tutorialhub.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","tutorialhub.jar"]