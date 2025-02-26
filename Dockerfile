FROM openjdk:17-jdk-slim as builder

WORKDIR /app

COPY gradle gradle
COPY gradlew settings.gradle build.gradle ./
RUN chmod +x gradlew

RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew build -x test --no-daemon

FROM openjdk:17-slim
WORKDIR /app

COPY --from=builder /app/build/libs/app.jar ./

RUN useradd -m appuser && chown -R appuser:appuser /app
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]