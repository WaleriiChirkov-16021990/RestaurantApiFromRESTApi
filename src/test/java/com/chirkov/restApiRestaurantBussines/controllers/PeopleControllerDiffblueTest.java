package com.chirkov.restApiRestaurantBussines.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.dto.PersonDto;
import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.repositories.DiscountsRepository;
import com.chirkov.restApiRestaurantBussines.repositories.PeopleRepository;
import com.chirkov.restApiRestaurantBussines.repositories.RoleRepository;
import com.chirkov.restApiRestaurantBussines.services.DiscountService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.RoleService;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.PersonErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotDelete;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotUpdatedException;
import com.chirkov.restApiRestaurantBussines.units.validators.PersonDtoValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {PeopleController.class})
@ExtendWith(SpringExtension.class)
class PeopleControllerDiffblueTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private PeopleController peopleController;

    @MockBean
    private PeopleService peopleService;

    @MockBean
    private PersonDtoValidator personDtoValidator;

    /**
     * Method under test: {@link PeopleController#handlerException(PersonNotDelete)}
     */
    @Test
    void testHandlerException() {
        ResponseEntity<PersonErrorResponse> actualHandlerExceptionResult = peopleController
                .handlerException(new PersonNotDelete("Not all who wander are lost"));
        PersonErrorResponse body = actualHandlerExceptionResult.getBody();
        assertEquals("Not all who wander are lost", body.message());
        assertEquals("PersonNotDelete", body.entityObjectName());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandlerExceptionResult.getStatusCode());
        assertTrue(actualHandlerExceptionResult.hasBody());
        assertTrue(actualHandlerExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link PeopleController#handlerException(PersonNotDelete)}
     */
    @Test
    void testHandlerException2() {
        PersonNotDelete notDelete = mock(PersonNotDelete.class);
        when(notDelete.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<PersonErrorResponse> actualHandlerExceptionResult = peopleController.handlerException(notDelete);
        verify(notDelete).getMessage();
        PersonErrorResponse body = actualHandlerExceptionResult.getBody();
        assertEquals("Not all who wander are lost", body.message());
        assertEquals("PersonNotDelete$MockitoMock$Q2Zvso2R", body.entityObjectName());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandlerExceptionResult.getStatusCode());
        assertTrue(actualHandlerExceptionResult.hasBody());
        assertTrue(actualHandlerExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link PeopleController#handlerException(PersonNotUpdatedException)}
     */
    @Test
    void testHandlerException3() {
        ResponseEntity<PersonErrorResponse> actualHandlerExceptionResult = peopleController
                .handlerException(new PersonNotUpdatedException("An error occurred"));
        PersonErrorResponse body = actualHandlerExceptionResult.getBody();
        assertEquals("An error occurred", body.message());
        assertEquals("PersonNotUpdatedException", body.entityObjectName());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandlerExceptionResult.getStatusCode());
        assertTrue(actualHandlerExceptionResult.hasBody());
        assertTrue(actualHandlerExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link PeopleController#handlerException(PersonNotUpdatedException)}
     */
    @Test
    void testHandlerException4() {
        PersonNotUpdatedException exception = mock(PersonNotUpdatedException.class);
        when(exception.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<PersonErrorResponse> actualHandlerExceptionResult = peopleController.handlerException(exception);
        verify(exception).getMessage();
        PersonErrorResponse body = actualHandlerExceptionResult.getBody();
        assertEquals("Not all who wander are lost", body.message());
        assertEquals("PersonNotUpdatedException$MockitoMock$alkppw9J", body.entityObjectName());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandlerExceptionResult.getStatusCode());
        assertTrue(actualHandlerExceptionResult.hasBody());
        assertTrue(actualHandlerExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link PeopleController#create(PersonDto, BindingResult)}
     */
    @Test
    void testCreate() throws Exception {
        when(peopleService.findAll()).thenReturn(new ArrayList<>());

        PersonDto personDto = new PersonDto();
        personDto.setEmail("jane.doe@example.org");
        personDto.setLastName("Doe");
        personDto.setName("Name");
        personDto.setPassword("iloveyou");
        personDto.setPhoneNumber("6625550144");
        personDto.setUsername("janedoe");
        personDto.setYearOfBirth(1);
        String content = (new ObjectMapper()).writeValueAsString(personDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(peopleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PeopleController#delete(Long)}
     */
    @Test
    void testDelete() throws Exception {
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
        doNothing().when(peopleService).deleteById(Mockito.<Long>any());
        when(peopleService.findById(Mockito.<Long>any())).thenReturn(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/people/{id}", 1L);
        MockMvcBuilders.standaloneSetup(peopleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("\"OK\""));
    }

    /**
     * Method under test:
     * {@link PeopleController#update(Long, PersonDto, BindingResult)}
     */
    @Test
    void testUpdate() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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
        PeopleRepository peopleRepository = mock(PeopleRepository.class);
        when(peopleRepository.save(Mockito.<Person>any())).thenReturn(person);
        Argon2PasswordEncoder bCryptPasswordEncoder = new Argon2PasswordEncoder();
        RoleService roleService = new RoleService(mock(RoleRepository.class));
        PeopleService peopleService = new PeopleService(peopleRepository, bCryptPasswordEncoder, roleService,
                new DiscountService(mock(DiscountsRepository.class)));

        ModelMapper modelMapper = new ModelMapper();
        PeopleRepository peopleRepository2 = mock(PeopleRepository.class);
        Argon2PasswordEncoder bCryptPasswordEncoder2 = new Argon2PasswordEncoder();
        RoleService roleService2 = new RoleService(mock(RoleRepository.class));
        PeopleController peopleController = new PeopleController(modelMapper, peopleService,
                new PersonDtoValidator(new PeopleService(peopleRepository2, bCryptPasswordEncoder2, roleService2,
                        new DiscountService(mock(DiscountsRepository.class)))));

        PersonDto person2 = new PersonDto();
        person2.setEmail("jane.doe@example.org");
        person2.setLastName("Doe");
        person2.setName("Name");
        person2.setPassword("iloveyou");
        person2.setPhoneNumber("6625550144");
        person2.setUsername("janedoe");
        person2.setYearOfBirth(1);
        ResponseEntity<HttpStatus> actualUpdateResult = peopleController.update(1L, person2,
                new BindException("Target", "Object Name"));
        verify(peopleRepository).save(Mockito.<Person>any());
        assertEquals(HttpStatus.OK, actualUpdateResult.getBody());
        assertEquals(HttpStatus.OK, actualUpdateResult.getStatusCode());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link PeopleController#update(Long, PersonDto, BindingResult)}
     */
    @Test
    void testUpdate2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Person>>any())).thenReturn(person);

        Discount discount2 = new Discount();
        discount2.setId(1L);
        discount2.setName("Name");
        discount2.setPersonList(new ArrayList<>());
        discount2.setSale(DiscountEnum.ZERO);

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");
        role2.setPersonList(new ArrayList<>());
        role2.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person2 = new Person();
        person2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person2.setCreatedReserveRecords(new ArrayList<>());
        person2.setDiscount(discount2);
        person2.setEmail("jane.doe@example.org");
        person2.setId(1L);
        person2.setLastName("Doe");
        person2.setName("Name");
        person2.setOrderList(new ArrayList<>());
        person2.setPassword("iloveyou");
        person2.setPhoneNumber("6625550144");
        person2.setReservationList(new ArrayList<>());
        person2.setRestaurantReviews(new ArrayList<>());
        person2.setRole(role2);
        person2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person2.setUpdatedReserveRecords(new ArrayList<>());
        person2.setUpdatedWho("2020-03-01");
        person2.setUsername("janedoe");
        person2.setYearOfBirth(1);
        PeopleRepository peopleRepository = mock(PeopleRepository.class);
        when(peopleRepository.save(Mockito.<Person>any())).thenReturn(person2);
        Argon2PasswordEncoder bCryptPasswordEncoder = new Argon2PasswordEncoder();
        RoleService roleService = new RoleService(mock(RoleRepository.class));
        PeopleService peopleService = new PeopleService(peopleRepository, bCryptPasswordEncoder, roleService,
                new DiscountService(mock(DiscountsRepository.class)));

        PeopleRepository peopleRepository2 = mock(PeopleRepository.class);
        Argon2PasswordEncoder bCryptPasswordEncoder2 = new Argon2PasswordEncoder();
        RoleService roleService2 = new RoleService(mock(RoleRepository.class));
        PeopleController peopleController = new PeopleController(modelMapper, peopleService,
                new PersonDtoValidator(new PeopleService(peopleRepository2, bCryptPasswordEncoder2, roleService2,
                        new DiscountService(mock(DiscountsRepository.class)))));

        PersonDto person3 = new PersonDto();
        person3.setEmail("jane.doe@example.org");
        person3.setLastName("Doe");
        person3.setName("Name");
        person3.setPassword("iloveyou");
        person3.setPhoneNumber("6625550144");
        person3.setUsername("janedoe");
        person3.setYearOfBirth(1);
        ResponseEntity<HttpStatus> actualUpdateResult = peopleController.update(1L, person3,
                new BindException("Target", "Object Name"));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Person>>any());
        verify(peopleRepository).save(Mockito.<Person>any());
        assertEquals(HttpStatus.OK, actualUpdateResult.getBody());
        assertEquals(HttpStatus.OK, actualUpdateResult.getStatusCode());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link PeopleController#update(Long, PersonDto, BindingResult)}
     */
    @Test
    void testUpdate3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Person>>any())).thenReturn(person);

        Discount discount2 = new Discount();
        discount2.setId(1L);
        discount2.setName("Name");
        discount2.setPersonList(new ArrayList<>());
        discount2.setSale(DiscountEnum.ZERO);

        Role role2 = new Role();
        role2.setId(1L);
        role2.setName("Name");
        role2.setPersonList(new ArrayList<>());
        role2.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person2 = new Person();
        person2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person2.setCreatedReserveRecords(new ArrayList<>());
        person2.setDiscount(discount2);
        person2.setEmail("jane.doe@example.org");
        person2.setId(1L);
        person2.setLastName("Doe");
        person2.setName("Name");
        person2.setOrderList(new ArrayList<>());
        person2.setPassword("iloveyou");
        person2.setPhoneNumber("6625550144");
        person2.setReservationList(new ArrayList<>());
        person2.setRestaurantReviews(new ArrayList<>());
        person2.setRole(role2);
        person2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person2.setUpdatedReserveRecords(new ArrayList<>());
        person2.setUpdatedWho("2020-03-01");
        person2.setUsername("janedoe");
        person2.setYearOfBirth(1);
        PeopleService peopleService = mock(PeopleService.class);
        when(peopleService.update(Mockito.<Long>any(), Mockito.<Person>any())).thenReturn(person2);
        PeopleRepository peopleRepository = mock(PeopleRepository.class);
        Argon2PasswordEncoder bCryptPasswordEncoder = new Argon2PasswordEncoder();
        RoleService roleService = new RoleService(mock(RoleRepository.class));
        PeopleController peopleController = new PeopleController(modelMapper, peopleService,
                new PersonDtoValidator(new PeopleService(peopleRepository, bCryptPasswordEncoder, roleService,
                        new DiscountService(mock(DiscountsRepository.class)))));

        PersonDto person3 = new PersonDto();
        person3.setEmail("jane.doe@example.org");
        person3.setLastName("Doe");
        person3.setName("Name");
        person3.setPassword("iloveyou");
        person3.setPhoneNumber("6625550144");
        person3.setUsername("janedoe");
        person3.setYearOfBirth(1);
        ResponseEntity<HttpStatus> actualUpdateResult = peopleController.update(1L, person3,
                new BindException("Target", "Object Name"));
        verify(peopleService).update(Mockito.<Long>any(), Mockito.<Person>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Person>>any());
        assertEquals(HttpStatus.OK, actualUpdateResult.getBody());
        assertEquals(HttpStatus.OK, actualUpdateResult.getStatusCode());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link PeopleController#update(Long, PersonDto, BindingResult)}
     */
    @Test
    void testUpdate4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

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
        PeopleRepository peopleRepository = mock(PeopleRepository.class);
        when(peopleRepository.save(Mockito.<Person>any())).thenReturn(person);
        Argon2PasswordEncoder bCryptPasswordEncoder = new Argon2PasswordEncoder();
        RoleService roleService = new RoleService(mock(RoleRepository.class));
        PeopleService peopleService = new PeopleService(peopleRepository, bCryptPasswordEncoder, roleService,
                new DiscountService(mock(DiscountsRepository.class)));

        ModelMapper modelMapper = new ModelMapper();
        PeopleRepository peopleRepository2 = mock(PeopleRepository.class);
        Argon2PasswordEncoder bCryptPasswordEncoder2 = new Argon2PasswordEncoder();
        RoleService roleService2 = new RoleService(mock(RoleRepository.class));
        PeopleController peopleController = new PeopleController(modelMapper, peopleService,
                new PersonDtoValidator(new PeopleService(peopleRepository2, bCryptPasswordEncoder2, roleService2,
                        new DiscountService(mock(DiscountsRepository.class)))));
        PersonDto person2 = mock(PersonDto.class);
        when(person2.getYearOfBirth()).thenReturn(1);
        when(person2.getEmail()).thenReturn("jane.doe@example.org");
        when(person2.getLastName()).thenReturn("Doe");
        when(person2.getName()).thenReturn("Name");
        when(person2.getPassword()).thenReturn("iloveyou");
        when(person2.getPhoneNumber()).thenReturn("6625550144");
        when(person2.getUsername()).thenReturn("janedoe");
        ResponseEntity<HttpStatus> actualUpdateResult = peopleController.update(1L, person2,
                new BindException("Target", "Object Name"));
        verify(person2).getEmail();
        verify(person2).getLastName();
        verify(person2).getName();
        verify(person2).getPassword();
        verify(person2).getPhoneNumber();
        verify(person2).getUsername();
        verify(person2).getYearOfBirth();
        verify(peopleRepository).save(Mockito.<Person>any());
        assertEquals(HttpStatus.OK, actualUpdateResult.getBody());
        assertEquals(HttpStatus.OK, actualUpdateResult.getStatusCode());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link PeopleController#create(PersonDto, BindingResult)}
     */
    @Test
    void testCreate2() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setEmail("jane.doe@example.org");
        personDto.setLastName("Doe");
        personDto.setName("Name");
        personDto.setPassword("iloveyou");
        personDto.setPhoneNumber("6625550144");
        personDto.setUsername("janedoe");
        personDto.setYearOfBirth(1);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PersonDto>>any())).thenReturn(personDto);

        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("?");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);

        Role role = new Role();
        role.setId(1L);
        role.setName("?");
        role.setPersonList(new ArrayList<>());
        role.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person = new Person();
        person.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setCreatedReserveRecords(new ArrayList<>());
        person.setDiscount(discount);
        person.setEmail("jane.doe@example.org");
        person.setId(1L);
        person.setLastName("Doe");
        person.setName("?");
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

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        when(peopleService.findAll()).thenReturn(personList);

        PersonDto personDto2 = new PersonDto();
        personDto2.setEmail("jane.doe@example.org");
        personDto2.setLastName("Doe");
        personDto2.setName("Name");
        personDto2.setPassword("iloveyou");
        personDto2.setPhoneNumber("6625550144");
        personDto2.setUsername("janedoe");
        personDto2.setYearOfBirth(1);
        String content = (new ObjectMapper()).writeValueAsString(personDto2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(peopleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1,\"phoneNumber\":\"6625550144\",\"email\":\"jane.doe@example"
                                        + ".org\",\"username\":\"janedoe\",\"password\":\"iloveyou\"}]"));
    }

    /**
     * Method under test: {@link PeopleController#create(PersonDto, BindingResult)}
     */
    @Test
    void testCreate3() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setEmail("jane.doe@example.org");
        personDto.setLastName("Doe");
        personDto.setName("Name");
        personDto.setPassword("iloveyou");
        personDto.setPhoneNumber("6625550144");
        personDto.setUsername("janedoe");
        personDto.setYearOfBirth(1);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PersonDto>>any())).thenReturn(personDto);

        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("?");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);

        Role role = new Role();
        role.setId(1L);
        role.setName("?");
        role.setPersonList(new ArrayList<>());
        role.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person = new Person();
        person.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setCreatedReserveRecords(new ArrayList<>());
        person.setDiscount(discount);
        person.setEmail("jane.doe@example.org");
        person.setId(1L);
        person.setLastName("Doe");
        person.setName("?");
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

        Discount discount2 = new Discount();
        discount2.setId(2L);
        discount2.setName("Name");
        discount2.setPersonList(new ArrayList<>());
        discount2.setSale(DiscountEnum.FIVE);

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("Name");
        role2.setPersonList(new ArrayList<>());
        role2.setRoleValue(RoleEnum.ROLE_USER);

        Person person2 = new Person();
        person2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person2.setCreatedReserveRecords(new ArrayList<>());
        person2.setDiscount(discount2);
        person2.setEmail("john.smith@example.org");
        person2.setId(2L);
        person2.setLastName("Smith");
        person2.setName("Name");
        person2.setOrderList(new ArrayList<>());
        person2.setPassword("?");
        person2.setPhoneNumber("8605550118");
        person2.setReservationList(new ArrayList<>());
        person2.setRestaurantReviews(new ArrayList<>());
        person2.setRole(role2);
        person2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person2.setUpdatedReserveRecords(new ArrayList<>());
        person2.setUpdatedWho("2020/03/01");
        person2.setUsername("?");
        person2.setYearOfBirth(0);

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person2);
        personList.add(person);
        when(peopleService.findAll()).thenReturn(personList);

        PersonDto personDto2 = new PersonDto();
        personDto2.setEmail("jane.doe@example.org");
        personDto2.setLastName("Doe");
        personDto2.setName("Name");
        personDto2.setPassword("iloveyou");
        personDto2.setPhoneNumber("6625550144");
        personDto2.setUsername("janedoe");
        personDto2.setYearOfBirth(1);
        String content = (new ObjectMapper()).writeValueAsString(personDto2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(peopleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1,\"phoneNumber\":\"6625550144\",\"email\":\"jane.doe@example"
                                        + ".org\",\"username\":\"janedoe\",\"password\":\"iloveyou\"},{\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1"
                                        + ",\"phoneNumber\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"password\":\"iloveyou"
                                        + "\"}]"));
    }

    /**
     * Method under test: {@link PeopleController#edit(Long, Model)}
     */
    @Test
    void testEdit() throws Exception {
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
        when(peopleService.findById(Mockito.<Long>any())).thenReturn(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people/{id}/edit", 1L);
        MockMvcBuilders.standaloneSetup(peopleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("\"OK\""));
    }

    /**
     * Method under test: {@link PeopleController#getPeople()}
     */
    @Test
    void testGetPeople() throws Exception {
        when(peopleService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people");
        MockMvcBuilders.standaloneSetup(peopleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PeopleController#getPeople()}
     */
    @Test
    void testGetPeople2() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setEmail("jane.doe@example.org");
        personDto.setLastName("Doe");
        personDto.setName("Name");
        personDto.setPassword("iloveyou");
        personDto.setPhoneNumber("6625550144");
        personDto.setUsername("janedoe");
        personDto.setYearOfBirth(1);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PersonDto>>any())).thenReturn(personDto);

        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("?");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);

        Role role = new Role();
        role.setId(1L);
        role.setName("?");
        role.setPersonList(new ArrayList<>());
        role.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person = new Person();
        person.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setCreatedReserveRecords(new ArrayList<>());
        person.setDiscount(discount);
        person.setEmail("jane.doe@example.org");
        person.setId(1L);
        person.setLastName("Doe");
        person.setName("?");
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

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);
        when(peopleService.findAll()).thenReturn(personList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people");
        MockMvcBuilders.standaloneSetup(peopleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1,\"phoneNumber\":\"6625550144\",\"email\":\"jane.doe@example"
                                        + ".org\",\"username\":\"janedoe\",\"password\":\"iloveyou\"}]"));
    }

    /**
     * Method under test: {@link PeopleController#getPeople()}
     */
    @Test
    void testGetPeople3() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setEmail("jane.doe@example.org");
        personDto.setLastName("Doe");
        personDto.setName("Name");
        personDto.setPassword("iloveyou");
        personDto.setPhoneNumber("6625550144");
        personDto.setUsername("janedoe");
        personDto.setYearOfBirth(1);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PersonDto>>any())).thenReturn(personDto);

        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("?");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);

        Role role = new Role();
        role.setId(1L);
        role.setName("?");
        role.setPersonList(new ArrayList<>());
        role.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person = new Person();
        person.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setCreatedReserveRecords(new ArrayList<>());
        person.setDiscount(discount);
        person.setEmail("jane.doe@example.org");
        person.setId(1L);
        person.setLastName("Doe");
        person.setName("?");
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

        Discount discount2 = new Discount();
        discount2.setId(2L);
        discount2.setName("Name");
        discount2.setPersonList(new ArrayList<>());
        discount2.setSale(DiscountEnum.FIVE);

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("Name");
        role2.setPersonList(new ArrayList<>());
        role2.setRoleValue(RoleEnum.ROLE_USER);

        Person person2 = new Person();
        person2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person2.setCreatedReserveRecords(new ArrayList<>());
        person2.setDiscount(discount2);
        person2.setEmail("john.smith@example.org");
        person2.setId(2L);
        person2.setLastName("Smith");
        person2.setName("Name");
        person2.setOrderList(new ArrayList<>());
        person2.setPassword("?");
        person2.setPhoneNumber("8605550118");
        person2.setReservationList(new ArrayList<>());
        person2.setRestaurantReviews(new ArrayList<>());
        person2.setRole(role2);
        person2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person2.setUpdatedReserveRecords(new ArrayList<>());
        person2.setUpdatedWho("2020/03/01");
        person2.setUsername("?");
        person2.setYearOfBirth(0);

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person2);
        personList.add(person);
        when(peopleService.findAll()).thenReturn(personList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people");
        MockMvcBuilders.standaloneSetup(peopleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1,\"phoneNumber\":\"6625550144\",\"email\":\"jane.doe@example"
                                        + ".org\",\"username\":\"janedoe\",\"password\":\"iloveyou\"},{\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1"
                                        + ",\"phoneNumber\":\"6625550144\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"password\":\"iloveyou"
                                        + "\"}]"));
    }

    /**
     * Method under test: {@link PeopleController#getPerson(Long)}
     */
    @Test
    void testGetPerson() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setEmail("jane.doe@example.org");
        personDto.setLastName("Doe");
        personDto.setName("Name");
        personDto.setPassword("iloveyou");
        personDto.setPhoneNumber("6625550144");
        personDto.setUsername("janedoe");
        personDto.setYearOfBirth(1);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PersonDto>>any())).thenReturn(personDto);

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
        when(peopleService.findById(Mockito.<Long>any())).thenReturn(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people/{id}", 1L);
        MockMvcBuilders.standaloneSetup(peopleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"name\":\"Name\",\"lastName\":\"Doe\",\"yearOfBirth\":1,\"phoneNumber\":\"6625550144\",\"email\":\"jane.doe@example"
                                        + ".org\",\"username\":\"janedoe\",\"password\":\"iloveyou\"}"));
    }
}
