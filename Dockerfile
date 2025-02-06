FROM eclipse-temurin:21-jdk-jammy AS build
LABEL authors="juan.ojeda"

WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle settings.gradle /app/

RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew clean build -x test --no-daemon

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /app/build/libs/aws-1.0.0.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "aws-1.0.0.jar", "-Dspring.config.name=application,secrets"]