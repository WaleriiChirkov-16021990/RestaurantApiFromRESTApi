package com.chirkov.restApiRestaurantBussines;

import com.github.javafaker.Faker;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiRestaurantBussinesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiRestaurantBussinesApplication.class, args);
    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void onApplicationReady() {
//    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Faker getFaker() {
        return new Faker();
    }


}
