# FROM Docker

1. Open project with IDEA
2. Setup variable env JAVA_HOME (jdk - 17)
3. Add the path to the PATH environment variable:%JAVA_HOME%/bin
4. Create a new jar file (./mvnw clean package -DskipTests)
5. Copy the jar file to the directory specified by the command (cp ./target/rest-api-reastaurant-bussiness-0.0.2.jar ./src/main/docker/)
6. cd ./scr/main/docker
7. Start the Docker daemon
8. docker-compose up
9. Check that the container is running: docker ps
