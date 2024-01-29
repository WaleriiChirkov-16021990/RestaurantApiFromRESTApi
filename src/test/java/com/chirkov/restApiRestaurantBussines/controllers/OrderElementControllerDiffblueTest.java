package com.chirkov.restApiRestaurantBussines.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.dto.OrderElementDto;
import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.models.StatusFromOrder;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.services.OrderElementsService;
import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.OrderElementErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.OrderElementsValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {OrderElementController.class})
@ExtendWith(SpringExtension.class)
class OrderElementControllerDiffblueTest {
    @MockBean
    private DishesService dishesService;

    @Autowired
    private OrderElementController orderElementController;

    @MockBean
    private OrderElementsService orderElementsService;

    @MockBean
    private OrderElementsValidator orderElementsValidator;

    @MockBean
    private OrderService orderService;

    /**
     * Method under test:
     * {@link OrderElementController#addOrder(OrderElementDto, BindingResult)}
     */
    @Test
    void testAddOrder() throws Exception {
        when(orderElementsService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/order-element")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new OrderElementDto(1L, 1L, 1)));
        MockMvcBuilders.standaloneSetup(orderElementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderElementController#getOrderElementsById(long)}
     */
    @Test
    void testGetOrderElementsById() throws Exception {
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

        OrderElements orderElements = new OrderElements();
        orderElements.setCount(3);
        orderElements.setDishes(dishes);
        orderElements.setId(1L);
        orderElements.setOrder(order);
        when(orderElementsService.findById(anyLong())).thenReturn(orderElements);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order-element/{id}", 1L);
        MockMvcBuilders.standaloneSetup(orderElementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"order\":{\"id\":1,\"price\":10.0,\"statusFromOrder\":\"OPEN\"},\"dishes\":{\"id\":1,\"name\":\"Name\",\"price"
                                        + "\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1,\"fats\":1,\"carbohydrates\":1,\"imageURL\":\"https://example"
                                        + ".org/example\",\"foodReviewList\":[],\"compositionsOfDishesList\":[]},\"count\":3}"));
    }

    /**
     * Method under test:
     * {@link OrderElementController#handlerException(OrderElementNotCreatedException)}
     */
    @Test
    void testHandlerException() {
        ResponseEntity<OrderElementErrorResponse> actualHandlerExceptionResult = orderElementController
                .handlerException(new OrderElementNotCreatedException("An error occurred"));
        OrderElementErrorResponse body = actualHandlerExceptionResult.getBody();
        assertEquals("An error occurred", body.message());
        assertEquals("OrderElementNotCreatedException", body.entityObjectName());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandlerExceptionResult.getStatusCode());
        assertTrue(actualHandlerExceptionResult.hasBody());
        assertTrue(actualHandlerExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderElementController#handlerException(OrderElementNotCreatedException)}
     */
    @Test
    void testHandlerException2() {
        OrderElementNotCreatedException exception = mock(OrderElementNotCreatedException.class);
        when(exception.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<OrderElementErrorResponse> actualHandlerExceptionResult = orderElementController
                .handlerException(exception);
        verify(exception).getMessage();
        OrderElementErrorResponse body = actualHandlerExceptionResult.getBody();
        assertEquals("Not all who wander are lost", body.message());
        assertEquals("OrderElementNotCreatedException$MockitoMock$UOQy9c5r", body.entityObjectName());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandlerExceptionResult.getStatusCode());
        assertTrue(actualHandlerExceptionResult.hasBody());
        assertTrue(actualHandlerExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderElementController#handlerException(OrderElementNotFoundException)}
     */
    @Test
    void testHandlerException3() {
        ResponseEntity<OrderElementErrorResponse> actualHandlerExceptionResult = orderElementController
                .handlerException(new OrderElementNotFoundException("An error occurred"));
        OrderElementErrorResponse body = actualHandlerExceptionResult.getBody();
        assertEquals("An error occurred", body.message());
        assertEquals("OrderElementNotFoundException", body.entityObjectName());
        assertEquals(HttpStatus.NOT_FOUND, actualHandlerExceptionResult.getStatusCode());
        assertTrue(actualHandlerExceptionResult.hasBody());
        assertTrue(actualHandlerExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderElementController#handlerException(OrderElementNotFoundException)}
     */
    @Test
    void testHandlerException4() {
        OrderElementNotFoundException exception = mock(OrderElementNotFoundException.class);
        when(exception.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<OrderElementErrorResponse> actualHandlerExceptionResult = orderElementController
                .handlerException(exception);
        verify(exception).getMessage();
        OrderElementErrorResponse body = actualHandlerExceptionResult.getBody();
        assertEquals("Not all who wander are lost", body.message());
        assertEquals("OrderElementNotFoundException$MockitoMock$qZBeBsNS", body.entityObjectName());
        assertEquals(HttpStatus.NOT_FOUND, actualHandlerExceptionResult.getStatusCode());
        assertTrue(actualHandlerExceptionResult.hasBody());
        assertTrue(actualHandlerExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link OrderElementController#getOrderElementByOrder(Order)}
     */
    @Test
    void testGetOrderElementByOrder() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order-element/order/{order}", order);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderElementController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }

    /**
     * Method under test: {@link OrderElementController#getOrderElements()}
     */
    @Test
    void testGetOrderElements() throws Exception {
        when(orderElementsService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order-element");
        MockMvcBuilders.standaloneSetup(orderElementController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
