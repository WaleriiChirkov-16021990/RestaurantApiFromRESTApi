package com.chirkov.restApiRestaurantBussines.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonDto {
    @NotNull(message = "Name is not null")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    @Column(name = "person_name")
    private String name;

    @NotNull(message = "Surname is not null")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    @Column(name = "person_lastname")
    private String lastName;

    @Min(value = 1900, message = "Year of birth > 1900")
    @NotNull(message = "Year of birth is not null")
    @Column(name = "person_year_of_birth")
    private int yearOfBirth;

    @Column(name = "person_phone_number")
    @NotNull(message = "PhoneNumber is not null")
    @Size(min = 11, max = 11, message = "Mobile knife must contains strictly 11 digits")
    private String phoneNumber;

    @Column(name = "person_email")
    @NotNull(message = "Email is not null")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "person_password")
    @NotNull(message = "Password is not null")
    @Size(min = 6, message = "Password contains min 6 symbol")
    private String password;
}
