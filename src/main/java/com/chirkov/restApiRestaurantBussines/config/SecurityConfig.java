package com.chirkov.restApiRestaurantBussines.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //настраивает аутентификацию
    protected void configurer(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(); //TODO
    }

}
