package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.services.IngredientsService;
import com.chirkov.restApiRestaurantBussines.units.validators.IngredientsValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ContextConfiguration(classes = {IngredientsController.class})
@ExtendWith(SpringExtension.class)
class IngredientsControllerDiffblueTest {
    @Autowired
    private IngredientsController ingredientsController;

    @MockBean
    private IngredientsService ingredientsService;

    @MockBean
    private IngredientsValidator ingredientsValidator;

    /**
     * Method under test:
     * {@link IngredientsController#addIngredient(Ingredients, BindingResult)}
     */
    @Test
    void testAddIngredient() throws Exception {
        when(ingredientsService.findAll()).thenReturn(new ArrayList<>());

        Ingredients ingredients = new Ingredients();
        ingredients.setCompositionsOfDishesList(new ArrayList<>());
        ingredients.setDescription("The characteristics of someone or something");
        ingredients.setId(1L);
        ingredients.setIngredientName("Ingredient Name");
        ingredients.setRemnant(1);
        ingredients.setSpicy(true);
        ingredients.setVegan(true);
        String content = (new ObjectMapper()).writeValueAsString(ingredients);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ingredients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(ingredientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link IngredientsController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(ingredientsService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ingredients");
        MockMvcBuilders.standaloneSetup(ingredientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link IngredientsController#getIngredientsById(long)}
     */
    @Test
    void testGetIngredientsById() throws Exception {
        Ingredients ingredients = new Ingredients();
        ingredients.setCompositionsOfDishesList(new ArrayList<>());
        ingredients.setDescription("The characteristics of someone or something");
        ingredients.setId(1L);
        ingredients.setIngredientName("Ingredient Name");
        ingredients.setRemnant(1);
        ingredients.setSpicy(true);
        ingredients.setVegan(true);
        when(ingredientsService.getById(anyLong())).thenReturn(ingredients);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ingredients/{id}", 1L);
        MockMvcBuilders.standaloneSetup(ingredientsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"ingredientName\":\"Ingredient Name\",\"remnant\":1,\"description\":\"The characteristics of someone"
                                        + " or something\",\"vegan\":true,\"spicy\":true}"));
    }
}
