1. Необходим [Docker](https://www.docker.com/products/docker-desktop/ "Docker Desktop")
2. Собираем Jar-file этого проекта удобным для вас способом, например, 
открыв консоль в корне проекта выполните команду 
```shell
./mvnw clean package -DskipTests
```
и копируем его из папки ./target где нам будет удобно с ним работать.
3. Используя этот jar-file создаем Image of the Docker командой из папки, 
куда переместили jar-file, предварительно создав в этой папке Dockerfile.

[//]: # (###     Пример DockerFile находится в проекте по пути)

[//]: # (      src/main/docker)

####  Пример DockerFile

```dockerfile
FROM openjdk:17-oracle
LABEL authors="wchirkov"
LABEL maintainer="bukin64@bk.ru"
LABEL version="0.0.2"
LABEL description="Docker image for RestApiRestaurantBussines"
LABEL name="rest-api-restaurant-bussines"
LABEL org.opencontainers.image.source="https://github.com/WaleriiChirkov-16021990/RestaurantApiFromRESTApi.git"
ARG JAR_FILE=*.jar
WORKDIR /app
COPY ${JAR_FILE} app.jar
EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]
```
Выполняемая команда:
```shell
docker build -t rest-api-restaurant-bussines .
```

4. Теперь опишем файл docker-compose.yml
```yaml
   version: '3.9'

services:
app:
container_name: app-spring-boot
image: rest-api-restaurant-bussines:latest
build:
context: .
ports:
- "8081:8080"
depends_on:
- db
environment:
- SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/postgres
- SPRING_DATASOURCE_USERNAME=postgres
- SPRING_DATASOURCE_PASSWORD=postgres
- SPRING_JPA_SHOW_SQL=true
- SERVER_PORT=8080
- SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
- SPRING_JPA_HIBERNATE_DDL_AUTO=create
- SPRING_DATASOURCE_INITIALIZE=true
- SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
- SPRING_HIBERNATE_SHOW_SQL=true
#      - SPRING_HIBERNATE_FORMAT_SQL=true
      - SPRING_JACKSON_SERIALIZATION_FAIL_ON_EMPTY_BEANS=false
      - TOKEN.SIGNING.KEY=53A73E5F1C4E0A2D3B5F2D784E6A1B423D6F247D1F6F5C3A596D635A75327855


db:
container_name: db-2
image: postgres:14-alpine
ports:
- "5432:5432"
environment:
- POSTGRES_USER=postgres
- POSTGRES_PASSWORD=postgres
- POSTGRES_HOST_AUTH_METHOD=trust
- POSTGRES_DB=postgres
volumes:
#      - ./data/postgres:/docker-entrypoint-initdb.d
- ./data/postgres:/var/lib/postgresql/data

```

5. Запустите сборку и старт контейнеров командой из консоли 
```bash
docker-compose up 
```
6. Теперь, если вы все это развернули на локальной машине - ваше приложение отвечает на localhost:8081
7. С помощью Docker приложение можно развернуть где угодно. Для работы приложения всегда поднимается 2 контейнера,
- база данных

- приложение, которое обращается к этой базе данных по открытому порту 5432. 

Можно дополнительно поднимать контейнер PGAdmin и следить за состоянием базы данных, администрировать ее.
В проекте есть папка где есть все необходимые файлы :
```shell
cd src/main/docker
```