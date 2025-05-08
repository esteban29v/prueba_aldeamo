# 📦 Prueba Técnica - Sistema de Envío de Mensajes Asíncronos

Este proyecto es una solución para una prueba técnica que implementa un sistema de envío y procesamiento de mensajes de manera asíncrona utilizando Spring Boot, RabbitMQ, MySQL y MongoDB, todo orquestado con Docker Compose.

## 📘 Descripción General

La solución está compuesta por dos microservicios:

- **messagepublisher**: Expone una API REST que recibe solicitudes de mensajes, valida su contenido y los envía a una cola en RabbitMQ.
- **messageconsumer**: Escucha mensajes desde la cola, aplica lógica de negocio (ej. limitar 3 mensajes por destino cada 24h), y persiste los datos en MongoDB.

## ⚙️ Tecnologías Usadas

- Java 17 + Spring Boot
- Maven
- Docker + Docker Compose
- RabbitMQ
- MySQL
- MongoDB
- Lombok
- Spring Data JPA & Spring Data MongoDB
- Bean Validation (JSR-380)

## 📁 Estructura del Proyecto

prueba_aldeamo/
├── docker-compose.yml
├── mysql/
│ └── mysql.init.sql
├── messagepublisher/
│ ├── Dockerfile
│ ├── pom.xml
│ └── src/
├── messageconsumer/
│ ├── Dockerfile
│ ├── pom.xml
│ └── src/


## 🧪 Requisitos Funcionales

- Validación de campos en la petición (`origin`, `destination`, `messageType`, `content`)
- `origin` debe existir en MySQL (líneas predefinidas)
- Se limita a **3 mensajes por destino en un periodo de 24 horas**
- Si excede el límite, se guarda el error en MongoDB
- Los mensajes válidos se envían a RabbitMQ con un header `timestamp`
- El consumidor calcula el tiempo de procesamiento y lo guarda en MongoDB

## 🧰 Requisitos del Entorno

Antes de ejecutar, asegúrate de tener instaladas las siguientes herramientas:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Postman](https://www.postman.com/) o similar para probar endpoints
- [MongoDB Compass](https://www.mongodb.com/products/compass) para inspeccionar datos
- [MySQL Client](https://dev.mysql.com/downloads/workbench/) para ver datos de origen

## 🚀 Instrucciones para Levantar el Proyecto

1. Clona el repositorio general:

```bash
git clone https://github.com/tuusuario/prueba_aldeamo.git
cd prueba_aldeamo

2. Ejecuta Docker Compose:

docker-compose up -d
```

3. Esto levantará:

MySQL (puerto 3307)

RabbitMQ (puerto 5672 y 15672 para el panel web)

MongoDB (puerto 27017)

messagepublisher (puerto 8080)

messageconsumer (internamente escucha desde la cola)

La base de datos MySQL se inicializa automáticamente con las líneas de origen autorizadas desde mysql/mysql.init.sql.