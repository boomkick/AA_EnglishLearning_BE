# ── Stage 1: Build with Gradle ────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY gradle/ gradle/
COPY gradlew gradlew.bat settings.gradle gradle.properties build.gradle ./
RUN chmod +x gradlew && ./gradlew --no-daemon dependencies

COPY src/ src/
RUN ./gradlew build -x test --no-daemon

# ── Stage 2: JVM runtime ─────────────────────────────
FROM eclipse-temurin:21-jre-alpine AS runner
WORKDIR /deployments

COPY --from=build /app/build/quarkus-app/lib/ lib/
COPY --from=build /app/build/quarkus-app/*.jar ./
COPY --from=build /app/build/quarkus-app/app/ app/
COPY --from=build /app/build/quarkus-app/quarkus/ quarkus/

EXPOSE 8080

ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]
