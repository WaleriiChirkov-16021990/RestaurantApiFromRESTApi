package com.chirkov.restApiRestaurantBussines.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.Objects;

@Getter
@Setter
public class PersonDto {

    @NotNull(message = "Name DTO is not null")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    private String name;

    @NotNull(message = "Lastname DTO is not null")
    @Size(min = 2, max = 100, message = "Name length is between 2 and 100 symbol")
    private String lastName;

    @Min(value = 1900, message = "Year of birth > 1900")
    @NotNull(message = "Year of birth DTO is not null")
    private int yearOfBirth;

//    @Size(min = 11, max = 11, message = "Mobile number must contains strictly 11 digits")
    @NotNull(message = "PhoneNumber DTO is not null")
    @Pattern(regexp = "^89\\d{9}$", message = "Your phoneNumber should be contains 11 digits and begin '89...'")
    private String phoneNumber;

    @NotNull(message = "Email DTO is not null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Username DTO is not null")
    private String username;

    @NotNull(message = "Password DTO is not null")
    @Size(min = 6, message = "Password contains min 6 symbol")
    private String password;

    public PersonDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDto personDto)) return false;
        return getYearOfBirth() == personDto.getYearOfBirth() && Objects.equals(getName(), personDto.getName()) && Objects.equals(getLastName(), personDto.getLastName()) && Objects.equals(getPhoneNumber(), personDto.getPhoneNumber()) && Objects.equals(getEmail(), personDto.getEmail()) && Objects.equals(getUsername(), personDto.getUsername()) && Objects.equals(getPassword(), personDto.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName(), getYearOfBirth(), getPhoneNumber(), getEmail(), getUsername(), getPassword());
    }
}
