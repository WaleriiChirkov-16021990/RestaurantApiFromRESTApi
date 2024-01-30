package com.chirkov.restApiRestaurantBussines.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.dto.OrderDto;
import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.models.StatusFromOrder;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.OrderErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.OrderValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerDiffblueTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderValidator orderValidator;

    @MockBean
    private PeopleService peopleService;

    /**
     * Method under test: {@link OrderController#findAllOrders()}
     */
    @Test
    void testFindAllOrders() throws Exception {
        when(orderService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/all");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#findById(Long)}
     */
    @Test
    void testFindById() throws Exception {
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
        when(orderService.findById(Mockito.<Long>any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"price\":10.0,\"statusFromOrder\":\"OPEN\"}"));
    }

    /**
     * Method under test:
     * {@link OrderController#handleOrder(OrderNotCreatedException)}
     */
    @Test
    void testHandleOrder() {
        ResponseEntity<OrderErrorResponse> actualHandleOrderResult = orderController
                .handleOrder(new OrderNotCreatedException("An error occurred"));
        OrderErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("An error occurred", body.message());
        assertEquals("OrderNotCreatedException", body.entityObjectName());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderController#handleOrder(OrderNotCreatedException)}
     */
    @Test
    void testHandleOrder2() {
        OrderNotCreatedException createdException = mock(OrderNotCreatedException.class);
        when(createdException.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<OrderErrorResponse> actualHandleOrderResult = orderController.handleOrder(createdException);
        verify(createdException).getMessage();
        OrderErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("Not all who wander are lost", body.message());
        assertEquals("OrderNotCreatedException$MockitoMock$XYYwA0Fa", body.entityObjectName());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderController#handleOrder(OrderNotFoundException)}
     */
    @Test
    void testHandleOrder3() {
        ResponseEntity<OrderErrorResponse> actualHandleOrderResult = orderController
                .handleOrder(new OrderNotFoundException("An error occurred"));
        OrderErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("An error occurred", body.message());
        assertEquals("OrderNotFoundException", body.entityObjectName());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderController#handleOrder(OrderNotFoundException)}
     */
    @Test
    void testHandleOrder4() {
        OrderNotFoundException foundException = mock(OrderNotFoundException.class);
        when(foundException.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<OrderErrorResponse> actualHandleOrderResult = orderController.handleOrder(foundException);
        verify(foundException).getMessage();
        OrderErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("Not all who wander are lost", body.message());
        assertEquals("OrderNotFoundException$MockitoMock$Vi1ZiCsg", body.entityObjectName());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderController#handleOrder(PersonNotFoundException)}
     */
    @Test
    void testHandleOrder5() {
        ResponseEntity<OrderErrorResponse> actualHandleOrderResult = orderController
                .handleOrder(new PersonNotFoundException("An error occurred"));
        OrderErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("An error occurred", body.message());
        assertEquals("PersonNotFoundException", body.entityObjectName());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderController#handleOrder(PersonNotFoundException)}
     */
    @Test
    void testHandleOrder6() {
        PersonNotFoundException foundException = mock(PersonNotFoundException.class);
        when(foundException.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<OrderErrorResponse> actualHandleOrderResult = orderController.handleOrder(foundException);
        verify(foundException).getMessage();
        OrderErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("Not all who wander are lost", body.message());
        assertEquals("PersonNotFoundException$MockitoMock$c2zR7QKf", body.entityObjectName());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link OrderController#findAllOrders()}
     */
    @Test
    void testFindAllOrders2() throws Exception {
        when(orderService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#save(OrderDto, BindingResult)}
     */
    @Test
    void testSave() throws Exception {
        when(orderService.findAll()).thenReturn(new ArrayList<>());

        OrderDto orderDto = new OrderDto();
        orderDto.setOwner(1L);
        orderDto.setPrice(10.0d);
        orderDto.setStatusFromOrder("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(orderDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
