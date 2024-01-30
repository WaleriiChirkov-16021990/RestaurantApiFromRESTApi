package com.chirkov.restApiRestaurantBussines.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.dto.TableReservationDto;
import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.ReserveTable;
import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTableEnum;
import com.chirkov.restApiRestaurantBussines.models.TableReservation;
import com.chirkov.restApiRestaurantBussines.repositories.DiscountsRepository;
import com.chirkov.restApiRestaurantBussines.repositories.PeopleRepository;
import com.chirkov.restApiRestaurantBussines.repositories.ReserveTableRepository;
import com.chirkov.restApiRestaurantBussines.repositories.RoleRepository;
import com.chirkov.restApiRestaurantBussines.repositories.TableReservationRepository;
import com.chirkov.restApiRestaurantBussines.services.DiscountService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.services.ReserveTableService;
import com.chirkov.restApiRestaurantBussines.services.RoleService;
import com.chirkov.restApiRestaurantBussines.services.TableReservationService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.TableReservationNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.validators.TableReservationValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {TableReservationController.class})
@ExtendWith(SpringExtension.class)
class TableReservationControllerDiffblueTest {
    @MockBean
    private PeopleService peopleService;

    @MockBean
    private ReserveTableService reserveTableService;

    @Autowired
    private TableReservationController tableReservationController;

    @MockBean
    private TableReservationService tableReservationService;

    @MockBean
    private TableReservationValidator tableReservationValidator;

    /**
     * Method under test:
     * {@link TableReservationController#getAllTableReservations()}
     */
    @Test
    void testGetAllTableReservations() throws Exception {
        when(tableReservationService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/table-reservations");
        MockMvcBuilders.standaloneSetup(tableReservationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link TableReservationController#getTableReservationById(Long)}
     */
    @Test
    void testGetTableReservationById() throws Exception {
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

        Person authorOfUpdate = new Person();
        authorOfUpdate.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authorOfUpdate.setCreatedReserveRecords(new ArrayList<>());
        authorOfUpdate.setDiscount(discount);
        authorOfUpdate.setEmail("jane.doe@example.org");
        authorOfUpdate.setId(1L);
        authorOfUpdate.setLastName("Doe");
        authorOfUpdate.setName("Name");
        authorOfUpdate.setOrderList(new ArrayList<>());
        authorOfUpdate.setPassword("iloveyou");
        authorOfUpdate.setPhoneNumber("6625550144");
        authorOfUpdate.setReservationList(new ArrayList<>());
        authorOfUpdate.setRestaurantReviews(new ArrayList<>());
        authorOfUpdate.setRole(role);
        authorOfUpdate.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authorOfUpdate.setUpdatedReserveRecords(new ArrayList<>());
        authorOfUpdate.setUpdatedWho("2020-03-01");
        authorOfUpdate.setUsername("janedoe");
        authorOfUpdate.setYearOfBirth(1);

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

        Person authorThisRecords = new Person();
        authorThisRecords.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authorThisRecords.setCreatedReserveRecords(new ArrayList<>());
        authorThisRecords.setDiscount(discount2);
        authorThisRecords.setEmail("jane.doe@example.org");
        authorThisRecords.setId(1L);
        authorThisRecords.setLastName("Doe");
        authorThisRecords.setName("Name");
        authorThisRecords.setOrderList(new ArrayList<>());
        authorThisRecords.setPassword("iloveyou");
        authorThisRecords.setPhoneNumber("6625550144");
        authorThisRecords.setReservationList(new ArrayList<>());
        authorThisRecords.setRestaurantReviews(new ArrayList<>());
        authorThisRecords.setRole(role2);
        authorThisRecords.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authorThisRecords.setUpdatedReserveRecords(new ArrayList<>());
        authorThisRecords.setUpdatedWho("2020-03-01");
        authorThisRecords.setUsername("janedoe");
        authorThisRecords.setYearOfBirth(1);

        Discount discount3 = new Discount();
        discount3.setId(1L);
        discount3.setName("Name");
        discount3.setPersonList(new ArrayList<>());
        discount3.setSale(DiscountEnum.ZERO);

        Role role3 = new Role();
        role3.setId(1L);
        role3.setName("Name");
        role3.setPersonList(new ArrayList<>());
        role3.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person owner = new Person();
        owner.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setCreatedReserveRecords(new ArrayList<>());
        owner.setDiscount(discount3);
        owner.setEmail("jane.doe@example.org");
        owner.setId(1L);
        owner.setLastName("Doe");
        owner.setName("Name");
        owner.setOrderList(new ArrayList<>());
        owner.setPassword("iloveyou");
        owner.setPhoneNumber("6625550144");
        owner.setReservationList(new ArrayList<>());
        owner.setRestaurantReviews(new ArrayList<>());
        owner.setRole(role3);
        owner.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setUpdatedReserveRecords(new ArrayList<>());
        owner.setUpdatedWho("2020-03-01");
        owner.setUsername("janedoe");
        owner.setYearOfBirth(1);

        StateFromTable stateFromTable = new StateFromTable();
        stateFromTable.setId(1L);
        stateFromTable.setName("Name");
        stateFromTable.setValue(StateFromTableEnum.READY_TO_BOARD);

        ReserveTable table = new ReserveTable();
        table.setAccommodationNumber(10);
        table.setId(1L);
        table.setNumberOfSeats(10);
        table.setReservationList(new ArrayList<>());
        table.setStateFromTable(stateFromTable);

        TableReservation tableReservation = new TableReservation();
        tableReservation.setAuthorOfUpdate(authorOfUpdate);
        tableReservation.setAuthorThisRecords(authorThisRecords);
        tableReservation.setCreate_at(LocalDate.of(1970, 1, 1).atStartOfDay());
        tableReservation.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        tableReservation.setId(1L);
        tableReservation.setNumberOfGuests(10);
        tableReservation.setOwner(owner);
        tableReservation.setTable(table);
        tableReservation.setUpdate_at(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(tableReservationService.findById(Mockito.<Long>any())).thenReturn(tableReservation);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/table-reservations/{id}", 1L);
        MockMvcBuilders.standaloneSetup(tableReservationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"date\":[1970,1,1,0,0],\"numberOfGuests\":10,\"create_at\":[1970,1,1,0,0],\"update_at\":[1970"
                                        + ",1,1,0,0]}"));
    }

    /**
     * Method under test:
     * {@link TableReservationController#save(TableReservationDto, BindingResult)}
     */
    @Test
    void testSave() throws TableReservationNotCreatedException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.chirkov.restApiRestaurantBussines.dto.TableReservationDto["date"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1300)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4568)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3821)
        //   See https://diff.blue/R013 to resolve this issue.

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

        Person authorOfUpdate = new Person();
        authorOfUpdate.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authorOfUpdate.setCreatedReserveRecords(new ArrayList<>());
        authorOfUpdate.setDiscount(discount);
        authorOfUpdate.setEmail("jane.doe@example.org");
        authorOfUpdate.setId(1L);
        authorOfUpdate.setLastName("Doe");
        authorOfUpdate.setName("Name");
        authorOfUpdate.setOrderList(new ArrayList<>());
        authorOfUpdate.setPassword("iloveyou");
        authorOfUpdate.setPhoneNumber("6625550144");
        authorOfUpdate.setReservationList(new ArrayList<>());
        authorOfUpdate.setRestaurantReviews(new ArrayList<>());
        authorOfUpdate.setRole(role);
        authorOfUpdate.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authorOfUpdate.setUpdatedReserveRecords(new ArrayList<>());
        authorOfUpdate.setUpdatedWho("2020-03-01");
        authorOfUpdate.setUsername("janedoe");
        authorOfUpdate.setYearOfBirth(1);

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

        Person authorThisRecords = new Person();
        authorThisRecords.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authorThisRecords.setCreatedReserveRecords(new ArrayList<>());
        authorThisRecords.setDiscount(discount2);
        authorThisRecords.setEmail("jane.doe@example.org");
        authorThisRecords.setId(1L);
        authorThisRecords.setLastName("Doe");
        authorThisRecords.setName("Name");
        authorThisRecords.setOrderList(new ArrayList<>());
        authorThisRecords.setPassword("iloveyou");
        authorThisRecords.setPhoneNumber("6625550144");
        authorThisRecords.setReservationList(new ArrayList<>());
        authorThisRecords.setRestaurantReviews(new ArrayList<>());
        authorThisRecords.setRole(role2);
        authorThisRecords.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        authorThisRecords.setUpdatedReserveRecords(new ArrayList<>());
        authorThisRecords.setUpdatedWho("2020-03-01");
        authorThisRecords.setUsername("janedoe");
        authorThisRecords.setYearOfBirth(1);

        Discount discount3 = new Discount();
        discount3.setId(1L);
        discount3.setName("Name");
        discount3.setPersonList(new ArrayList<>());
        discount3.setSale(DiscountEnum.ZERO);

        Role role3 = new Role();
        role3.setId(1L);
        role3.setName("Name");
        role3.setPersonList(new ArrayList<>());
        role3.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person owner = new Person();
        owner.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setCreatedReserveRecords(new ArrayList<>());
        owner.setDiscount(discount3);
        owner.setEmail("jane.doe@example.org");
        owner.setId(1L);
        owner.setLastName("Doe");
        owner.setName("Name");
        owner.setOrderList(new ArrayList<>());
        owner.setPassword("iloveyou");
        owner.setPhoneNumber("6625550144");
        owner.setReservationList(new ArrayList<>());
        owner.setRestaurantReviews(new ArrayList<>());
        owner.setRole(role3);
        owner.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setUpdatedReserveRecords(new ArrayList<>());
        owner.setUpdatedWho("2020-03-01");
        owner.setUsername("janedoe");
        owner.setYearOfBirth(1);

        StateFromTable stateFromTable = new StateFromTable();
        stateFromTable.setId(1L);
        stateFromTable.setName("Name");
        stateFromTable.setValue(StateFromTableEnum.READY_TO_BOARD);

        ReserveTable table = new ReserveTable();
        table.setAccommodationNumber(10);
        table.setId(1L);
        table.setNumberOfSeats(10);
        table.setReservationList(new ArrayList<>());
        table.setStateFromTable(stateFromTable);

        TableReservation tableReservation = new TableReservation();
        tableReservation.setAuthorOfUpdate(authorOfUpdate);
        tableReservation.setAuthorThisRecords(authorThisRecords);
        tableReservation.setCreate_at(LocalDate.of(1970, 1, 1).atStartOfDay());
        tableReservation.setDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        tableReservation.setId(1L);
        tableReservation.setNumberOfGuests(10);
        tableReservation.setOwner(owner);
        tableReservation.setTable(table);
        tableReservation.setUpdate_at(LocalDate.of(1970, 1, 1).atStartOfDay());
        TableReservationRepository repository = mock(TableReservationRepository.class);
        when(repository.save(Mockito.<TableReservation>any())).thenReturn(tableReservation);
        TableReservationService service = new TableReservationService(repository);
        TableReservationRepository repository2 = mock(TableReservationRepository.class);
        Optional<List<TableReservation>> ofResult = Optional.of(new ArrayList<>());
        when(repository2.getTableReservationByTable(Mockito.<ReserveTable>any())).thenReturn(ofResult);
        TableReservationValidator validator = new TableReservationValidator(new TableReservationService(repository2));

        Discount discount4 = new Discount();
        discount4.setId(1L);
        discount4.setName("Name");
        discount4.setPersonList(new ArrayList<>());
        discount4.setSale(DiscountEnum.ZERO);

        Role role4 = new Role();
        role4.setId(1L);
        role4.setName("Name");
        role4.setPersonList(new ArrayList<>());
        role4.setRoleValue(RoleEnum.ROLE_ADMIN);

        Person person = new Person();
        person.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setCreatedReserveRecords(new ArrayList<>());
        person.setDiscount(discount4);
        person.setEmail("jane.doe@example.org");
        person.setId(1L);
        person.setLastName("Doe");
        person.setName("Name");
        person.setOrderList(new ArrayList<>());
        person.setPassword("iloveyou");
        person.setPhoneNumber("6625550144");
        person.setReservationList(new ArrayList<>());
        person.setRestaurantReviews(new ArrayList<>());
        person.setRole(role4);
        person.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        person.setUpdatedReserveRecords(new ArrayList<>());
        person.setUpdatedWho("2020-03-01");
        person.setUsername("janedoe");
        person.setYearOfBirth(1);
        Optional<Person> ofResult2 = Optional.of(person);
        PeopleRepository peopleRepository = mock(PeopleRepository.class);
        when(peopleRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        Argon2PasswordEncoder bCryptPasswordEncoder = new Argon2PasswordEncoder();
        RoleService roleService = new RoleService(mock(RoleRepository.class));
        PeopleService peopleService = new PeopleService(peopleRepository, bCryptPasswordEncoder, roleService,
                new DiscountService(mock(DiscountsRepository.class)));

        StateFromTable stateFromTable2 = new StateFromTable();
        stateFromTable2.setId(1L);
        stateFromTable2.setName("Name");
        stateFromTable2.setValue(StateFromTableEnum.READY_TO_BOARD);

        ReserveTable reserveTable = new ReserveTable();
        reserveTable.setAccommodationNumber(10);
        reserveTable.setId(1L);
        reserveTable.setNumberOfSeats(10);
        reserveTable.setReservationList(new ArrayList<>());
        reserveTable.setStateFromTable(stateFromTable2);
        Optional<ReserveTable> ofResult3 = Optional.of(reserveTable);
        ReserveTableRepository repository3 = mock(ReserveTableRepository.class);
        when(repository3.findById(Mockito.<Long>any())).thenReturn(ofResult3);
        TableReservationController tableReservationController = new TableReservationController(service, validator,
                peopleService, new ReserveTableService(repository3));
        TableReservationDto reservationDto = new TableReservationDto();
        ResponseEntity<HttpStatus> actualSaveResult = tableReservationController.save(reservationDto,
                new BindException("Target", "Object Name"));
        verify(repository2).getTableReservationByTable(Mockito.<ReserveTable>any());
        verify(repository3).findById(Mockito.<Long>any());
        verify(peopleRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(repository).save(Mockito.<TableReservation>any());
        assertEquals(HttpStatus.OK, actualSaveResult.getBody());
        assertEquals(HttpStatus.OK, actualSaveResult.getStatusCode());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
    }
}
