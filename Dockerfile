# Usar la imagen oficial de OpenJDK 17
FROM openjdk:17-jdk-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el Maven Wrapper y el archivo pom.xml
COPY mvnw . 
COPY .mvn .mvn
COPY pom.xml .

# Descargar las dependencias de Maven
RUN ./mvnw dependency:go-offline

# Copiar el código fuente
COPY src /app/src

# Ejecutar el build de Maven
RUN ./mvnw clean package -DskipTests

# Verificar si el archivo .jar está presente en el directorio target
RUN ls /app/target

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

