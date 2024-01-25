package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.services.OrderService;
import com.chirkov.restApiRestaurantBussines.services.PeopleService;
import com.chirkov.restApiRestaurantBussines.units.validators.OrderValidator;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
}
