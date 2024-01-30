package com.chirkov.restApiRestaurantBussines.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.repositories.DiscountsRepository;
import com.chirkov.restApiRestaurantBussines.repositories.PeopleRepository;
import com.chirkov.restApiRestaurantBussines.repositories.RoleRepository;
import com.chirkov.restApiRestaurantBussines.services.DiscountService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.RoleService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderDto.class, PeopleService.class, RoleService.class, DiscountService.class})
@ExtendWith(SpringExtension.class)
class OrderDtoDiffblueTest {
    @MockBean
    private DiscountsRepository discountsRepository;

    @Autowired
    private OrderDto orderDto;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleService peopleService;

    @MockBean
    private RoleRepository roleRepository;

    /**
     * Method under test: {@link OrderDto#mappingbyOrder(PeopleService)}
     */
    @Test
    void testMappingbyOrder() throws PersonNotFoundException {
        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("Name");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        role.setPersonList(new ArrayList<>());
        role.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person = new Person();
        person.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setCreatedReserveRecords(new ArrayList<>());
        person.setDiscount(discount);
        person.setEmail("jane.doe@example.org");
        person.setId(1L);
        person.setLastName("Doe");
        person.setName("Name");
        person.setOrderList(new ArrayList<>());
        person.setPassword("iloveyou");
        person.setPhoneNumber("6625550144");
        person.setReservationList(new ArrayList<>());
        person.setRestaurantReviews(new ArrayList<>());
        person.setRole(role);
        person.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setUpdatedReserveRecords(new ArrayList<>());
        person.setUpdatedWho("2020-03-01");
        person.setUsername("janedoe");
        person.setYearOfBirth(1);
        Optional<Person> ofResult = Optional.of(person);
        when(peopleRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Order actualMappingbyOrderResult = orderDto.mappingbyOrder(peopleService);
        verify(peopleRepository).findById(Mockito.<Long>any());
        assertEquals(0.0d, actualMappingbyOrderResult.getPrice());
        assertSame(person, actualMappingbyOrderResult.getOwner());
    }

    /**
     * Method under test: {@link OrderDto#mappingbyOrder(PeopleService)}
     */
    @Test
    void testMappingbyOrder2() throws PersonNotFoundException {
        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("Name");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        role.setPersonList(new ArrayList<>());
        role.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person = new Person("Name", "Doe", 6, "6625550144", "jane.doe@example.org", "janedoe", "iloveyou");
        person.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setCreatedReserveRecords(new ArrayList<>());
        person.setDiscount(discount);
        person.setEmail("jane.doe@example.org");
        person.setId(1L);
        person.setLastName("Doe");
        person.setName("Name");
        person.setOrderList(new ArrayList<>());
        person.setPassword("iloveyou");
        person.setPhoneNumber("6625550144");
        person.setReservationList(new ArrayList<>());
        person.setRestaurantReviews(new ArrayList<>());
        person.setRole(role);
        person.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setUpdatedReserveRecords(new ArrayList<>());
        person.setUpdatedWho("2020-03-01");
        person.setUsername("janedoe");
        person.setYearOfBirth(1);
        Optional<Person> ofResult = Optional.of(person);
        when(peopleRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Order actualMappingbyOrderResult = orderDto.mappingbyOrder(peopleService);
        verify(peopleRepository).findById(Mockito.<Long>any());
        assertEquals(0.0d, actualMappingbyOrderResult.getPrice());
        assertSame(person, actualMappingbyOrderResult.getOwner());
    }
}
