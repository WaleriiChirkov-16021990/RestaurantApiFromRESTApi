package com.chirkov.restApiRestaurantBussines.security;

import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {


    private final PersonDetailsService personDetailsService;
    private final PeopleService peopleService;

    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService, PeopleService peopleService) {
        this.personDetailsService = personDetailsService;
        this.peopleService = peopleService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails personDetails = personDetailsService.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();
//        String encodePass = new BCryptPasswordEncoder().encode(password);

        if (!password.equals(personDetails.getPassword())) {
            throw new BadCredentialsException("incorrect password");
        }
        List<SimpleGrantedAuthority> roles = peopleService.findByName(username).getRole().stream().map((element) -> new SimpleGrantedAuthority(element.getName())).toList();    ;
        return new UsernamePasswordAuthenticationToken(personDetails, password, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
