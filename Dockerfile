# Usar la imagen oficial de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del proyecto a la imagen
COPY . .

# Exponer el puerto 8080
EXPOSE 8080

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Ejecutar el build de Maven
RUN mvn clean package

# Comando para ejecutar la aplicación con Spring Boot
CMD ["java", "-jar", "target/appVedruna-0.0.1-SNAPSHOT.jar"]
