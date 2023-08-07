# Use uma imagem base do OpenJDK para Java 11 (ou a versão que você está usando)
FROM openjdk:17-alpine

# Diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR da sua aplicação (certifique-se de que o nome do arquivo esteja correto)
COPY target/order-manager-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar a aplicação quando o contêiner iniciar
CMD ["java", "-jar", "app.jar"]