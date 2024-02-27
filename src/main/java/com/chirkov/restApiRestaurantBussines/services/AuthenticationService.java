package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.dto.JwtAuthenticationResponse;
import com.chirkov.restApiRestaurantBussines.dto.PersonDto;
import com.chirkov.restApiRestaurantBussines.dto.SignInRequest;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.security.PersonDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PeopleService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(PersonDto request) {
        Person person = mapper.map(request, Person.class);
        userService.save(person);
        var jwt = jwtService.generateToken(new PersonDetails(person));
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}