# Usar una imagen base de OpenJDK que también tenga Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias (esto optimiza el proceso de construcción)
COPY pom.xml .

# Descargar las dependencias
RUN mvn dependency:go-offline

# Copiar el código fuente del proyecto
COPY src /app/src

# Ejecutar la compilación de Maven para generar el archivo .jar
RUN mvn clean package -DskipTests

# Usar una imagen más pequeña para la aplicación final
FROM openjdk:17-jdk-slim

# Copiar el archivo .jar generado desde la imagen build
COPY --from=build /app/target/messagepublisher-0.0.1-SNAPSHOT.jar /app.jar

# Configurar el comando de entrada
ENTRYPOINT ["java", "-jar", "/app.jar"]