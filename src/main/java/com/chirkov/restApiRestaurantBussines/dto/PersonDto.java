package com.chirkov.restApiRestaurantBussines.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//
//import javax.persistence.Column;
//import javax.validation.constraints.*;
//import java.time.LocalDateTime;
//import java.time.Year;
//import java.util.Date;
//import java.util.Objects;
@Data
public class PersonDto {

    @NotNull(message = "Name DTO is not null")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    private String name;

    @NotNull(message = "Lastname DTO is not null")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    private String lastName;

    @Min(value = 1900, message = "Year of birth > 1900")
    @Max(value = 2024, message = "PersonDto/Year of birth < 2024")
    @NotNull(message = "Year of birth DTO is not null")
    private int yearOfBirth;

    @NotNull(message = "PhoneNumber DTO is not null")
    @Pattern(regexp = "^89\\d{9}$", message = "Your phoneNumber should be contains 11 digits and begin '89...'")
    private String phoneNumber;

    @NotNull(message = "Email DTO is not null")
    @NotEmpty(message = "Email DTO is not empty")
    @Size(max = 100, message = " characters email must not exceed 100 ")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Username DTO is not null")
    @NotEmpty(message = "Username should be valid")
    @Size(max = 100, message = " characters username must not exceed 100 ")
    private String username;

    @NotNull(message = "Password DTO is not null")
    @Size(min = 4, max = 150,message = "Password contains min 4 symbol characters and max 150 symbol characters")
    private String password;
}
