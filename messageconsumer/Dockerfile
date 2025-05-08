# Usar una imagen base de Maven con OpenJDK
FROM maven:3.8.4-openjdk-17-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .

# Descargar las dependencias (esto evita descargar dependencias cada vez que el código cambia)
RUN mvn dependency:go-offline

# Copiar todo el código fuente del proyecto
COPY . .

# Ejecutar la compilación de Maven para generar el archivo .jar
RUN mvn clean package -DskipTests

# Usar una imagen base más pequeña para la aplicación final
FROM openjdk:17-jdk-alpine

# Copiar el archivo .jar generado desde la imagen build
COPY --from=build /app/target/messageconsumer-0.0.1-SNAPSHOT.jar /app.jar

# Configurar el comando de entrada
ENTRYPOINT ["java", "-jar", "/app.jar"]