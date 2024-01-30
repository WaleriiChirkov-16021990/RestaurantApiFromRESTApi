package com.chirkov.restApiRestaurantBussines.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.services.DishesService;
import com.chirkov.restApiRestaurantBussines.units.errorResponses.DishesErrorResponse;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.validators.DishesValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@ContextConfiguration(classes = {DishesController.class})
@ExtendWith(SpringExtension.class)
class DishesControllerDiffblueTest {
    @Autowired
    private DishesController dishesController;

    @MockBean
    private DishesService dishesService;

    @MockBean
    private DishesValidator dishesValidator;

    /**
     * Method under test:
     * {@link DishesController#handleOrder(DishesNotCreatedException)}
     */
    @Test
    void testHandleOrder() {
        ResponseEntity<DishesErrorResponse> actualHandleOrderResult = dishesController
                .handleOrder(new DishesNotCreatedException("An error occurred"));
        DishesErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("An error occurred", body.message());
        assertEquals("DishesNotCreatedException", body.entityObjectName());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link DishesController#handleOrder(DishesNotCreatedException)}
     */
    @Test
    void testHandleOrder2() {
        DishesNotCreatedException foundException = mock(DishesNotCreatedException.class);
        when(foundException.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<DishesErrorResponse> actualHandleOrderResult = dishesController.handleOrder(foundException);
        verify(foundException).getMessage();
        DishesErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("DishesNotCreatedException$MockitoMock$UnuConCZ", body.entityObjectName());
        assertEquals("Not all who wander are lost", body.message());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link DishesController#handleOrder(DishesNotFoundException)}
     */
    @Test
    void testHandleOrder3() {
        ResponseEntity<DishesErrorResponse> actualHandleOrderResult = dishesController
                .handleOrder(new DishesNotFoundException("An error occurred"));
        DishesErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("An error occurred", body.message());
        assertEquals("DishesNotFoundException", body.entityObjectName());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link DishesController#handleOrder(DishesNotFoundException)}
     */
    @Test
    void testHandleOrder4() {
        DishesNotFoundException foundException = mock(DishesNotFoundException.class);
        when(foundException.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<DishesErrorResponse> actualHandleOrderResult = dishesController.handleOrder(foundException);
        verify(foundException).getMessage();
        DishesErrorResponse body = actualHandleOrderResult.getBody();
        assertEquals("DishesNotFoundException$MockitoMock$dJ19k4Km", body.entityObjectName());
        assertEquals("Not all who wander are lost", body.message());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleOrderResult.getStatusCode());
        assertTrue(actualHandleOrderResult.hasBody());
        assertTrue(actualHandleOrderResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link DishesController#getAllDishes()}
     */
    @Test
    void testGetAllDishes() throws Exception {
        when(dishesService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes");
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DishesController#getAllDishes()}
     */
    @Test
    void testGetAllDishes2() throws Exception {
        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("?");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        ArrayList<Dishes> dishesList = new ArrayList<>();
        dishesList.add(dishes);
        when(dishesService.findAll()).thenReturn(dishesList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes");
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"name\":\"?\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1,\"fats\":1,\"carbohydrates\":1,"
                                        + "\"imageURL\":\"https://example.org/example\",\"foodReviewList\":[],\"compositionsOfDishesList\":[]}]"));
    }

    /**
     * Method under test: {@link DishesController#getDishesById(long)}
     */
    @Test
    void testGetDishesById() throws Exception {
        when(dishesService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes/{id}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DishesController#getDishesById(long)}
     */
    @Test
    void testGetDishesById2() throws Exception {
        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("?");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        ArrayList<Dishes> dishesList = new ArrayList<>();
        dishesList.add(dishes);
        when(dishesService.findAll()).thenReturn(dishesList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes/{id}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"name\":\"?\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1,\"fats\":1,\"carbohydrates\":1,"
                                        + "\"imageURL\":\"https://example.org/example\",\"foodReviewList\":[],\"compositionsOfDishesList\":[]}]"));
    }

    /**
     * Method under test: {@link DishesController#getDishesByName(String)}
     */
    @Test
    void testGetDishesByName() throws Exception {
        when(dishesService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes/{name}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DishesController#getDishesByName(String)}
     */
    @Test
    void testGetDishesByName2() throws Exception {
        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("?");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        ArrayList<Dishes> dishesList = new ArrayList<>();
        dishesList.add(dishes);
        when(dishesService.findAll()).thenReturn(dishesList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes/{name}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"name\":\"?\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1,\"fats\":1,\"carbohydrates\":1,"
                                        + "\"imageURL\":\"https://example.org/example\",\"foodReviewList\":[],\"compositionsOfDishesList\":[]}]"));
    }

    /**
     * Method under test:
     * {@link DishesController#getDishesFromStartingWithName(String)}
     */
    @Test
    void testGetDishesFromStartingWithName() throws Exception {
        when(dishesService.getDishesByStartingWith(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes/name/{start}", "Start");
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link DishesController#getDishesFromStartingWithName(String)}
     */
    @Test
    void testGetDishesFromStartingWithName2() throws Exception {
        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("?");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        ArrayList<Dishes> dishesList = new ArrayList<>();
        dishesList.add(dishes);
        when(dishesService.getDishesByStartingWith(Mockito.<String>any())).thenReturn(dishesList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes/name/{start}", "Start");
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"name\":\"?\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1,\"fats\":1,\"carbohydrates\":1,"
                                        + "\"imageURL\":\"https://example.org/example\",\"foodReviewList\":[],\"compositionsOfDishesList\":[]}]"));
    }

    /**
     * Method under test: {@link DishesController#saveDishes(Dishes, BindingResult)}
     */
    @Test
    void testSaveDishes() throws Exception {
        when(dishesService.findAll()).thenReturn(new ArrayList<>());

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
        String content = (new ObjectMapper()).writeValueAsString(dishes);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DishesController#saveDishes(Dishes, BindingResult)}
     */
    @Test
    void testSaveDishes2() throws Exception {
        Dishes dishes = new Dishes();
        dishes.setCalories(1);
        dishes.setCarbohydrates(1);
        dishes.setCompositionsOfDishesList(new ArrayList<>());
        dishes.setFats(1);
        dishes.setFoodReviewList(new ArrayList<>());
        dishes.setId(1L);
        dishes.setImageURL("https://example.org/example");
        dishes.setName("?");
        dishes.setOrderElementsIntegerMap(new ArrayList<>());
        dishes.setPrice(10.0d);
        dishes.setProteins(1);
        dishes.setWeight(10.0d);

        ArrayList<Dishes> dishesList = new ArrayList<>();
        dishesList.add(dishes);
        when(dishesService.findAll()).thenReturn(dishesList);

        Dishes dishes2 = new Dishes();
        dishes2.setCalories(1);
        dishes2.setCarbohydrates(1);
        dishes2.setCompositionsOfDishesList(new ArrayList<>());
        dishes2.setFats(1);
        dishes2.setFoodReviewList(new ArrayList<>());
        dishes2.setId(1L);
        dishes2.setImageURL("https://example.org/example");
        dishes2.setName("Name");
        dishes2.setOrderElementsIntegerMap(new ArrayList<>());
        dishes2.setPrice(10.0d);
        dishes2.setProteins(1);
        dishes2.setWeight(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(dishes2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(dishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"name\":\"?\",\"price\":10.0,\"weight\":10.0,\"calories\":1,\"proteins\":1,\"fats\":1,\"carbohydrates\":1,"
                                        + "\"imageURL\":\"https://example.org/example\",\"foodReviewList\":[],\"compositionsOfDishesList\":[]}]"));
    }
}
