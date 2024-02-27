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
#ENTRYPOINT ["top", "-b"]