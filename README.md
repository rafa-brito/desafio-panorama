# desafio-panorama

para subir a aplicação é necessario ter instalado:
 - java 17+
 - maven
 - git
 - docker
 - docker-compose

tendo as dependencias mencionadas instaladas (com as variaveis de ambiente também configuradas), rodar comandos abaixo em sequência na pasta raiz do projeto:

1 - criação do jar:
```shell
mvn clean package
```
2 - subida do ambiente:
```shell
docker-compose up -d
```

O tanto o banco de dados como o sistema de messageria estão disponiveis em suas portas padrão na maquina HOST. a aplicação escuta a porta 8080 do HOST.