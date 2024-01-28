package com.chirkov.restApiRestaurantBussines.controllers;

import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.dto.CompositionsOfDishesDto;
import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.models.EnumUnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.services.CompositionsOfDishesService;
import com.chirkov.restApiRestaurantBussines.services.IngredientsService;
import com.chirkov.restApiRestaurantBussines.services.UnitsOfMeasurementService;
import com.chirkov.restApiRestaurantBussines.units.validators.CompositionsOfDishesValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {CompositionsOfDishesController.class})
@ExtendWith(SpringExtension.class)
class CompositionsOfDishesControllerDiffblueTest {
    @Autowired
    private CompositionsOfDishesController compositionsOfDishesController;

    @MockBean
    private CompositionsOfDishesService compositionsOfDishesService;

    @MockBean
    private CompositionsOfDishesValidator compositionsOfDishesValidator;

    @MockBean
    private IngredientsService ingredientsService;

    @MockBean
    private UnitsOfMeasurementService unitsOfMeasurementService;

    /**
     * Method under test:
     * {@link CompositionsOfDishesController#addCom(CompositionsOfDishesDto, BindingResult)}
     */
    @Test
    void testAddCom() throws Exception {
        when(compositionsOfDishesService.findAll()).thenReturn(new ArrayList<>());

        CompositionsOfDishesDto compositionsOfDishesDto = new CompositionsOfDishesDto();
        compositionsOfDishesDto.setCount(3);
        compositionsOfDishesDto.setIngredient(1L);
        compositionsOfDishesDto.setName("Name");
        compositionsOfDishesDto.setUnits(1L);
        String content = (new ObjectMapper()).writeValueAsString(compositionsOfDishesDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/composition_of_dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(compositionsOfDishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CompositionsOfDishesController#deleteCom(Long)}
     */
    @Test
    void testDeleteCom() throws Exception {
        Ingredients ingredient = new Ingredients();
        ingredient.setCompositionsOfDishesList(new ArrayList<>());
        ingredient.setDescription("The characteristics of someone or something");
        ingredient.setId(1L);
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setRemnant(1);
        ingredient.setSpicy(true);
        ingredient.setVegan(true);

        UnitsOfMeasurement units = new UnitsOfMeasurement();
        units.setCommentary("Commentary");
        units.setCompositionsOfDishesList(new ArrayList<>());
        units.setId(1L);
        units.setName("Name");
        units.setUnitOfMeasurement(EnumUnitsOfMeasurement.GRAMMES);

        CompositionsOfDishes compositionsOfDishes = new CompositionsOfDishes();
        compositionsOfDishes.setCount(3);
        compositionsOfDishes.setDishesList(new ArrayList<>());
        compositionsOfDishes.setId(1L);
        compositionsOfDishes.setIngredient(ingredient);
        compositionsOfDishes.setName("Name");
        compositionsOfDishes.setUnits(units);
        when(compositionsOfDishesService.deleteById(Mockito.<Long>any())).thenReturn(compositionsOfDishes);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/composition_of_dishes/{id}", 1L);
        MockMvcBuilders.standaloneSetup(compositionsOfDishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"ingredient\":{\"id\":1,\"ingredientName\":\"Ingredient Name\",\"remnant\":1,\"description\":\"The"
                                        + " characteristics of someone or something\",\"spicy\":true,\"vegan\":true},\"count\":3,\"units\":{\"id\":1,\"name"
                                        + "\":\"Name\",\"unitOfMeasurement\":\"GRAMMES\",\"commentary\":\"Commentary\"}}"));
    }

    /**
     * Method under test: {@link CompositionsOfDishesController#findComById(Long)}
     */
    @Test
    void testFindComById() throws Exception {
        Ingredients ingredient = new Ingredients();
        ingredient.setCompositionsOfDishesList(new ArrayList<>());
        ingredient.setDescription("The characteristics of someone or something");
        ingredient.setId(1L);
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setRemnant(1);
        ingredient.setSpicy(true);
        ingredient.setVegan(true);

        UnitsOfMeasurement units = new UnitsOfMeasurement();
        units.setCommentary("Commentary");
        units.setCompositionsOfDishesList(new ArrayList<>());
        units.setId(1L);
        units.setName("Name");
        units.setUnitOfMeasurement(EnumUnitsOfMeasurement.GRAMMES);

        CompositionsOfDishes compositionsOfDishes = new CompositionsOfDishes();
        compositionsOfDishes.setCount(3);
        compositionsOfDishes.setDishesList(new ArrayList<>());
        compositionsOfDishes.setId(1L);
        compositionsOfDishes.setIngredient(ingredient);
        compositionsOfDishes.setName("Name");
        compositionsOfDishes.setUnits(units);
        when(compositionsOfDishesService.findById(Mockito.<Long>any())).thenReturn(compositionsOfDishes);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/composition_of_dishes/{id}", 1L);
        MockMvcBuilders.standaloneSetup(compositionsOfDishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"ingredient\":{\"id\":1,\"ingredientName\":\"Ingredient Name\",\"remnant\":1,\"description\":\"The"
                                        + " characteristics of someone or something\",\"spicy\":true,\"vegan\":true},\"count\":3,\"units\":{\"id\":1,\"name"
                                        + "\":\"Name\",\"unitOfMeasurement\":\"GRAMMES\",\"commentary\":\"Commentary\"}}"));
    }

    /**
     * Method under test: {@link CompositionsOfDishesController#findComList()}
     */
    @Test
    void testFindComList() throws Exception {
        when(compositionsOfDishesService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/composition_of_dishes");
        MockMvcBuilders.standaloneSetup(compositionsOfDishesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
