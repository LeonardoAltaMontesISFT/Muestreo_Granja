FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B \
    && JAR_FILE=$(find target -maxdepth 1 -type f \
       -name "*.jar" ! -name "*.jar.original" | head -n 1) \
    && test -n "$JAR_FILE" \
    && cp "$JAR_FILE" app.jar

#Etapa 2
FROM eclipse-temurin:17-jre-noble
WORKDIR /app
RUN groupadd --system spring \
    && useradd --system --gid spring spring
COPY --from=build --chown=spring /app/app.jar app.jar 
USER spring:spring 
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
