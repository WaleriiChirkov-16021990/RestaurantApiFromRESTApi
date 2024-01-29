package com.chirkov.restApiRestaurantBussines.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.models.StatusFromOrder;
import com.chirkov.restApiRestaurantBussines.repositories.DishesRepository;
import com.chirkov.restApiRestaurantBussines.repositories.OrderRepository;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotCreatedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class OrderElementDtoDiffblueTest {
    /**
     * Method under test:
     * {@link OrderElementDto#mappingTransferObject(OrderService, DishesService)}
     */
    @Test
    void testMappingTransferObject() throws OrderElementNotCreatedException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.chirkov.restApiRestaurantBussines.services.DishesService
        //       com.chirkov.restApiRestaurantBussines.dto.OrderElementDto
        //       com.chirkov.restApiRestaurantBussines.services.OrderService
        //   See https://diff.blue/R027 to resolve this issue.

        OrderElementDto orderElementDto = new OrderElementDto(1L, 1L, 1);

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

        Person owner = new Person();
        owner.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setCreatedReserveRecords(new ArrayList<>());
        owner.setDiscount(discount);
        owner.setEmail("jane.doe@example.org");
        owner.setId(1L);
        owner.setLastName("Doe");
        owner.setName("Name");
        owner.setOrderList(new ArrayList<>());
        owner.setPassword("iloveyou");
        owner.setPhoneNumber("6625550144");
        owner.setReservationList(new ArrayList<>());
        owner.setRestaurantReviews(new ArrayList<>());
        owner.setRole(role);
        owner.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setUpdatedReserveRecords(new ArrayList<>());
        owner.setUpdatedWho("2020-03-01");
        owner.setUsername("janedoe");
        owner.setYearOfBirth(1);

        Order order = new Order();
        order.setId(1L);
        order.setOwner(owner);
        order.setPrice(10.0d);
        order.setStatusFromOrder(StatusFromOrder.OPEN);
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        OrderService orderService = new OrderService(orderRepository);

        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("Name");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);
        DishesRepository repository = mock(DishesRepository.class);
        when(repository.getReferenceById(Mockito.<Long>any())).thenReturn(dishes);
        OrderElements actualMappingTransferObjectResult = orderElementDto.mappingTransferObject(orderService,
                new DishesService(repository));
        verify(repository).getReferenceById(Mockito.<Long>any());
        verify(orderRepository).findById(Mockito.<Long>any());
        assertEquals(1, actualMappingTransferObjectResult.getCount());
        assertSame(dishes, actualMappingTransferObjectResult.getDishes());
        assertSame(order, actualMappingTransferObjectResult.getOrder());
    }

    /**
     * Method under test:
     * {@link OrderElementDto#mappingTransferObject(OrderService, DishesService)}
     */
    @Test
    void testMappingTransferObject2() throws OrderElementNotCreatedException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.chirkov.restApiRestaurantBussines.services.DishesService
        //       com.chirkov.restApiRestaurantBussines.dto.OrderElementDto
        //       com.chirkov.restApiRestaurantBussines.services.OrderService
        //   See https://diff.blue/R027 to resolve this issue.

        OrderElementDto orderElementDto = new OrderElementDto(1L, 1L, 1);

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

        Person owner = new Person();
        owner.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setCreatedReserveRecords(new ArrayList<>());
        owner.setDiscount(discount);
        owner.setEmail("jane.doe@example.org");
        owner.setId(1L);
        owner.setLastName("Doe");
        owner.setName("Name");
        owner.setOrderList(new ArrayList<>());
        owner.setPassword("iloveyou");
        owner.setPhoneNumber("6625550144");
        owner.setReservationList(new ArrayList<>());
        owner.setRestaurantReviews(new ArrayList<>());
        owner.setRole(role);
        owner.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setUpdatedReserveRecords(new ArrayList<>());
        owner.setUpdatedWho("2020-03-01");
        owner.setUsername("janedoe");
        owner.setYearOfBirth(1);

        Order order = new Order();
        order.setId(1L);
        order.setOwner(owner);
        order.setPrice(10.0d);
        order.setStatusFromOrder(StatusFromOrder.OPEN);
        Optional<Order> ofResult = Optional.of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        OrderService orderService = new OrderService(orderRepository);
        DishesRepository repository = mock(DishesRepository.class);
        when(repository.getReferenceById(Mockito.<Long>any()))
                .thenThrow(new OrderElementNotCreatedException("An error occurred"));
        assertThrows(OrderElementNotCreatedException.class,
                () -> orderElementDto.mappingTransferObject(orderService, new DishesService(repository)));
        verify(repository).getReferenceById(Mockito.<Long>any());
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link OrderElementDto#mappingTransferObject(OrderService, DishesService)}
     */
    @Test
    void testMappingTransferObject3() throws OrderElementNotCreatedException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.chirkov.restApiRestaurantBussines.services.DishesService
        //       com.chirkov.restApiRestaurantBussines.dto.OrderElementDto
        //       com.chirkov.restApiRestaurantBussines.services.OrderService
        //   See https://diff.blue/R027 to resolve this issue.

        OrderElementDto orderElementDto = new OrderElementDto(1L, 1L, 1);
        OrderRepository orderRepository = mock(OrderRepository.class);
        Optional<Order> emptyResult = Optional.empty();
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        OrderService orderService = new OrderService(orderRepository);
        assertThrows(OrderElementNotCreatedException.class,
                () -> orderElementDto.mappingTransferObject(orderService, new DishesService(mock(DishesRepository.class))));
        verify(orderRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link OrderElementDto#mappingTransferObject(OrderService, DishesService)}
     */
    @Test
    void testMappingTransferObject4() throws OrderElementNotCreatedException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.chirkov.restApiRestaurantBussines.services.DishesService
        //       com.chirkov.restApiRestaurantBussines.dto.OrderElementDto
        //       com.chirkov.restApiRestaurantBussines.services.OrderService
        //   See https://diff.blue/R027 to resolve this issue.

        OrderElementDto orderElementDto = new OrderElementDto(1L, 1L, 1);

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

        Person owner = new Person();
        owner.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setCreatedReserveRecords(new ArrayList<>());
        owner.setDiscount(discount);
        owner.setEmail("jane.doe@example.org");
        owner.setId(1L);
        owner.setLastName("Doe");
        owner.setName("Name");
        owner.setOrderList(new ArrayList<>());
        owner.setPassword("iloveyou");
        owner.setPhoneNumber("6625550144");
        owner.setReservationList(new ArrayList<>());
        owner.setRestaurantReviews(new ArrayList<>());
        owner.setRole(role);
        owner.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setUpdatedReserveRecords(new ArrayList<>());
        owner.setUpdatedWho("2020-03-01");
        owner.setUsername("janedoe");
        owner.setYearOfBirth(1);

        Order order = new Order();
        order.setId(1L);
        order.setOwner(owner);
        order.setPrice(10.0d);
        order.setStatusFromOrder(StatusFromOrder.OPEN);
        OrderService orderService = mock(OrderService.class);
        when(orderService.findById(Mockito.<Long>any())).thenReturn(order);

        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("Name");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);
        DishesRepository repository = mock(DishesRepository.class);
        when(repository.getReferenceById(Mockito.<Long>any())).thenReturn(dishes);
        OrderElements actualMappingTransferObjectResult = orderElementDto.mappingTransferObject(orderService,
                new DishesService(repository));
        verify(orderService).findById(Mockito.<Long>any());
        verify(repository).getReferenceById(Mockito.<Long>any());
        assertEquals(1, actualMappingTransferObjectResult.getCount());
        assertSame(dishes, actualMappingTransferObjectResult.getDishes());
        assertSame(order, actualMappingTransferObjectResult.getOrder());
    }

    /**
     * Method under test:
     * {@link OrderElementDto#mappingTransferObject(OrderService, DishesService)}
     */
    @Test
    void testMappingTransferObject5() throws DishesNotFoundException, OrderElementNotCreatedException {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R027 Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       com.chirkov.restApiRestaurantBussines.services.DishesService
        //       com.chirkov.restApiRestaurantBussines.dto.OrderElementDto
        //       com.chirkov.restApiRestaurantBussines.services.OrderService
        //   See https://diff.blue/R027 to resolve this issue.

        OrderElementDto orderElementDto = new OrderElementDto(1L, 1L, 1);

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

        Person owner = new Person();
        owner.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setCreatedReserveRecords(new ArrayList<>());
        owner.setDiscount(discount);
        owner.setEmail("jane.doe@example.org");
        owner.setId(1L);
        owner.setLastName("Doe");
        owner.setName("Name");
        owner.setOrderList(new ArrayList<>());
        owner.setPassword("iloveyou");
        owner.setPhoneNumber("6625550144");
        owner.setReservationList(new ArrayList<>());
        owner.setRestaurantReviews(new ArrayList<>());
        owner.setRole(role);
        owner.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        owner.setUpdatedReserveRecords(new ArrayList<>());
        owner.setUpdatedWho("2020-03-01");
        owner.setUsername("janedoe");
        owner.setYearOfBirth(1);

        Order order = new Order();
        order.setId(1L);
        order.setOwner(owner);
        order.setPrice(10.0d);
        order.setStatusFromOrder(StatusFromOrder.OPEN);
        OrderService orderService = mock(OrderService.class);
        when(orderService.findById(Mockito.<Long>any())).thenReturn(order);

        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("Name");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);
        DishesService dishesService = mock(DishesService.class);
        when(dishesService.findById(anyLong())).thenReturn(dishes);
        OrderElements actualMappingTransferObjectResult = orderElementDto.mappingTransferObject(orderService,
                dishesService);
        verify(dishesService).findById(anyLong());
        verify(orderService).findById(Mockito.<Long>any());
        assertEquals(1, actualMappingTransferObjectResult.getCount());
        assertSame(dishes, actualMappingTransferObjectResult.getDishes());
        assertSame(order, actualMappingTransferObjectResult.getOrder());
    }
}
