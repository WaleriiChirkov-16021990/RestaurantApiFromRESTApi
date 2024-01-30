package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.models.Discount;
import com.chirkov.restApiRestaurantBussines.models.DiscountEnum;
import com.chirkov.restApiRestaurantBussines.services.DiscountService;
import com.chirkov.restApiRestaurantBussines.units.validators.DiscountValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {DiscountController.class})
@ExtendWith(SpringExtension.class)
class DiscountControllerDiffblueTest {
    @Autowired
    private DiscountController discountController;

    @MockBean
    private DiscountService discountService;

    @MockBean
    private DiscountValidator discountValidator;

    /**
     * Method under test:
     * {@link DiscountController#addDiscount(Discount, BindingResult)}
     */
    @Test
    void testAddDiscount() throws Exception {
        when(discountService.findAll()).thenReturn(new ArrayList<>());

        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("Name");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);
        String content = (new ObjectMapper()).writeValueAsString(discount);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/discount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(discountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DiscountController#getAllDiscounts()}
     */
    @Test
    void testGetAllDiscounts() throws Exception {
        when(discountService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/discount");
        MockMvcBuilders.standaloneSetup(discountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DiscountController#getDiscount(Long)}
     */
    @Test
    void testGetDiscount() throws Exception {
        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("Name");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);
        when(discountService.findById(Mockito.<Long>any())).thenReturn(discount);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/discount/{id}", 1L);
        MockMvcBuilders.standaloneSetup(discountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"sale\":\"ZERO\"}"));
    }

    /**
     * Method under test: {@link DiscountController#getDiscount(String)}
     */
    @Test
    void testGetDiscount2() throws Exception {
        Discount discount = new Discount();
        discount.setId(1L);
        discount.setName("Name");
        discount.setPersonList(new ArrayList<>());
        discount.setSale(DiscountEnum.ZERO);
        Optional<Discount> ofResult = Optional.of(discount);
        when(discountService.findByName(Mockito.<String>any())).thenReturn(ofResult.get());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/discount/name/{name}", "Name");
        MockMvcBuilders.standaloneSetup(discountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"sale\":\"ZERO\"}"));
    }
}
