package com.chirkov.restApiRestaurantBussines.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.models.EnumUnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.models.UnitsOfMeasurement;
import com.chirkov.restApiRestaurantBussines.repositories.IngredientsRepository;
import com.chirkov.restApiRestaurantBussines.repositories.UnitsOfMeasurementRepository;
import com.chirkov.restApiRestaurantBussines.services.IngredientsService;
import com.chirkov.restApiRestaurantBussines.services.UnitsOfMeasurementService;
import com.chirkov.restApiRestaurantBussines.units.exceptions.CompositionsOfDishesNotCreatedException;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CompositionsOfDishesDto.class, IngredientsService.class,
        UnitsOfMeasurementService.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CompositionsOfDishesDtoDiffblueTest {
    @Autowired
    private CompositionsOfDishesDto compositionsOfDishesDto;

    @MockBean
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private IngredientsService ingredientsService;

    @MockBean
    private UnitsOfMeasurementRepository unitsOfMeasurementRepository;

    @Autowired
    private UnitsOfMeasurementService unitsOfMeasurementService;

    /**
     * Method under test:
     * {@link CompositionsOfDishesDto#mappingObject(IngredientsService, UnitsOfMeasurementService)}
     */
    @Test
    void testMappingObject() throws CompositionsOfDishesNotCreatedException {
        Ingredients ingredients = new Ingredients();
        ingredients.setCompositionsOfDishesList(new ArrayList<>());
        ingredients.setDescription("The characteristics of someone or something");
        ingredients.setId(1L);
        ingredients.setIngredientName("Ingredient Name");
        ingredients.setRemnant(1);
        ingredients.setSpicy(true);
        ingredients.setVegan(true);
        Optional<Ingredients> ofResult = Optional.of(ingredients);
        when(ingredientsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        UnitsOfMeasurement unitsOfMeasurement = new UnitsOfMeasurement();
        unitsOfMeasurement.setCommentary("Commentary");
        unitsOfMeasurement.setCompositionsOfDishesList(new ArrayList<>());
        unitsOfMeasurement.setId(1L);
        unitsOfMeasurement.setName("Name");
        unitsOfMeasurement.setUnitOfMeasurement(EnumUnitsOfMeasurement.GRAMMES);
        Optional<UnitsOfMeasurement> ofResult2 = Optional.of(unitsOfMeasurement);
        when(unitsOfMeasurementRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        CompositionsOfDishes actualMappingObjectResult = compositionsOfDishesDto.mappingObject(ingredientsService,
                unitsOfMeasurementService);
        verify(ingredientsRepository).findById(Mockito.<Long>any());
        verify(unitsOfMeasurementRepository).findById(Mockito.<Long>any());
        assertNull(actualMappingObjectResult.getName());
        assertEquals(0, actualMappingObjectResult.getCount());
        assertSame(ingredients, actualMappingObjectResult.getIngredient());
        assertSame(unitsOfMeasurement, actualMappingObjectResult.getUnits());
    }

    /**
     * Method under test:
     * {@link CompositionsOfDishesDto#mappingObject(IngredientsService, UnitsOfMeasurementService)}
     */
    @Test
    void testMappingObject2() throws CompositionsOfDishesNotCreatedException {
        Ingredients ingredients = new Ingredients();
        ingredients.setCompositionsOfDishesList(new ArrayList<>());
        ingredients.setDescription("The characteristics of someone or something");
        ingredients.setId(1L);
        ingredients.setIngredientName("Ingredient Name");
        ingredients.setRemnant(1);
        ingredients.setSpicy(true);
        ingredients.setVegan(true);
        Optional<Ingredients> ofResult = Optional.of(ingredients);
        when(ingredientsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(unitsOfMeasurementRepository.findById(Mockito.<Long>any()))
                .thenThrow(new CompositionsOfDishesNotCreatedException("An error occurred"));
        assertThrows(CompositionsOfDishesNotCreatedException.class,
                () -> compositionsOfDishesDto.mappingObject(ingredientsService, unitsOfMeasurementService));
        verify(ingredientsRepository).findById(Mockito.<Long>any());
        verify(unitsOfMeasurementRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link CompositionsOfDishesDto#mappingObject(IngredientsService, UnitsOfMeasurementService)}
     */
    @Test
    void testMappingObject3() throws CompositionsOfDishesNotCreatedException {
        Optional<Ingredients> emptyResult = Optional.empty();
        when(ingredientsRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        UnitsOfMeasurement unitsOfMeasurement = new UnitsOfMeasurement();
        unitsOfMeasurement.setCommentary("Commentary");
        unitsOfMeasurement.setCompositionsOfDishesList(new ArrayList<>());
        unitsOfMeasurement.setId(1L);
        unitsOfMeasurement.setName("Name");
        unitsOfMeasurement.setUnitOfMeasurement(EnumUnitsOfMeasurement.GRAMMES);
        Optional<UnitsOfMeasurement> ofResult = Optional.of(unitsOfMeasurement);
        when(unitsOfMeasurementRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertThrows(CompositionsOfDishesNotCreatedException.class,
                () -> compositionsOfDishesDto.mappingObject(ingredientsService, unitsOfMeasurementService));
        verify(ingredientsRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link CompositionsOfDishesDto#mappingObject(IngredientsService, UnitsOfMeasurementService)}
     */
    @Test
    void testMappingObject4() throws CompositionsOfDishesNotCreatedException {
        Ingredients ingredients = new Ingredients();
        ingredients.setCompositionsOfDishesList(new ArrayList<>());
        ingredients.setDescription("The characteristics of someone or something");
        ingredients.setId(1L);
        ingredients.setIngredientName("Ingredient Name");
        ingredients.setRemnant(1);
        ingredients.setSpicy(true);
        ingredients.setVegan(true);
        Optional<Ingredients> ofResult = Optional.of(ingredients);
        when(ingredientsRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<UnitsOfMeasurement> emptyResult = Optional.empty();
        when(unitsOfMeasurementRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(CompositionsOfDishesNotCreatedException.class,
                () -> compositionsOfDishesDto.mappingObject(ingredientsService, unitsOfMeasurementService));
        verify(ingredientsRepository).findById(Mockito.<Long>any());
        verify(unitsOfMeasurementRepository).findById(Mockito.<Long>any());
    }
}
