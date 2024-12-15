# Usar una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado por Maven/Gradle al contenedor
COPY target/credit-card-api-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que Spring Boot escucha
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]