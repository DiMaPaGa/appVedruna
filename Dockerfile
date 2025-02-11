# Usar la imagen oficial de OpenJDK 17
FROM openjdk:17-jdk-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar solo el archivo pom.xml primero para aprovechar el caché de Docker
COPY pom.xml .

# Descargar las dependencias de Maven (esto se cacheará para no descargarlo cada vez)
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src /app/src

# Ejecutar el build de Maven
RUN mvn clean package -DskipTests

# Crear la imagen final con el artefacto compilado
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR generado desde la imagen build
COPY --from=build /app/target/appVedruna-0.0.1-SNAPSHOT.jar /app/appVedruna.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación con Spring Boot
CMD ["java", "-jar", "appVedruna.jar"]

