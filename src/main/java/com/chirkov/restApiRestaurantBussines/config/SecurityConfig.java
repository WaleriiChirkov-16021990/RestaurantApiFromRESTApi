//package com.chirkov.restApiRestaurantBussines.config;
//
//import com.chirkov.restApiRestaurantBussines.services.PersonDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    //    private final AuthProviderImpl authProvider;
//    private final PersonDetailsService personDetailsService;
//
//    @Autowired
//    public SecurityConfig(PersonDetailsService personDetailsService) {
//        this.personDetailsService = personDetailsService;
//    }
////    @Autowired
////    public SecurityConfig(AuthProviderImpl authProvider) {
////        this.authProvider = authProvider;
////    }
//
//    //сюда поступает HTTP query
////    .csrf().disable()
////    ,"/ingredients","/unit-of-measurements","/composition_of_dishes","/people"
//
////    @Bean
////    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
////        return httpSecurity
////                .authorizeRequests(authorize -> {
////                            authorize
////                                    .antMatchers("/admin/**").hasAuthority("ADMIN")
////                                    .antMatchers("/user/**").hasAuthority("USER")
////                                    .antMatchers("/auth/**").authenticated()
////                                    .antMatchers("/public").permitAll()
////                                    .anyRequest().denyAll();
////                        }
////                )
////                .formLogin().and()
////                .httpBasic().and()
////                .csrf().disable()
////                .build();
////    }
//
//    //конфигурирую security
//    // и авторизацию
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
////                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/auth/login", "/auth/registration", "/error")
//                .permitAll()
//                .antMatchers("/public/**").hasAuthority("USER")
//                .antMatchers("/admin/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/auth/login")
//                .loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/people", true)
//                .failureUrl("/auth/login?error")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/auth/login");
//    }
//
//    //настраиваем аутентификацию
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(authProvider);
//        auth.userDetailsService(personDetailsService)
//                .passwordEncoder(getPasswordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
