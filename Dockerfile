FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/cps-backend-1.0.0.jar app.jar
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=5s --retries=3 CMD wget -qO- http://127.0.0.1:8080/actuator/health/readiness || exit 1
ENTRYPOINT ["java","-jar","/app/app.jar"]
