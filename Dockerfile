# Usar una imagen base de OpenJDK para Java 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Definir un argumento para el archivo JAR
ARG JAR_FILE=target/credit-card-api-0.0.1-SNAPSHOT.jar

# Copiar el archivo JAR generado al contenedor
COPY ${JAR_FILE} app.jar

# Exponer el puerto en el que Spring Boot escucha
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]