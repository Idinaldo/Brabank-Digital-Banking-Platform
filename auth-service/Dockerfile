FROM eclipse-temurin:21
COPY target/auth-service-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh
ENTRYPOINT ["java", "-jar", "/app.jar"]
