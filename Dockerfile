FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de ejecución de la biblioteca
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/biblioteca.salas.duoc-0.0.1-SNAPSHOT.jar biblioteca-service.jar


ENV PORT=8080
ENV SPRING_PROFILES_ACTIVE=local

EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "biblioteca-service.jar"]
