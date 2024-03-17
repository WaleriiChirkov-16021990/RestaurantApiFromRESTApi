package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.repositories.IngredientsRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.IngredientsServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.IngredientsEmptyListException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.IngredientsNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.IngredientsNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.IngredientsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED,
        rollbackFor = {IngredientsNotCreatedException.class, IngredientsNotDeletedException.class})
public class IngredientsService implements IngredientsServiceByRepository<Ingredients> {
    private final IngredientsRepository repository;
    @Autowired
    public IngredientsService(IngredientsRepository repository) {
        this.repository = repository;
    }

    public List<Ingredients> findAll() {
        List<Ingredients> ingredients;
        try {
            ingredients = repository.findAll();
        } catch (Exception e) {
            throw new IngredientsNotFoundException(e.getMessage(), e);
        }

        if (ingredients.isEmpty()) {
            throw new IngredientsEmptyListException("No ingredients. Available ingredients: " + ingredients);
        }
        return ingredients;
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Ingredients deleteById(Long id) {
        Ingredients deletedIngredients = findById(id);
        try {
            repository.deleteById(id);
            return deletedIngredients;
        } catch (Exception e) {
            throw new IngredientsNotDeletedException("Error deleting " + id + "__" + e.getMessage(), e);
        }
    }

    public Ingredients findByName(String name) {
        return repository.getByIngredientName(name)
                .orElseThrow(() ->
                        new IngredientsNotFoundException("Ingredients by name" + name + ", not found."));
    }

    public Optional<Ingredients> getOptionalByName(String name) {
        return repository.getByIngredientName(name);
    }

    public Ingredients findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new IngredientsNotFoundException("Ingredients by id" + id + ", not found."));
    }

    public List<Ingredients> getIngredientNameContaining(String name) {
        return repository.getByIngredientNameContaining(name)
                .orElseThrow(() ->
                        new IngredientsEmptyListException("Ingredients containing name = " + name + ", not found"));
    }

    public List<Ingredients> getIngredientNameStartWith(String name) {
        return repository.getByIngredientNameIsStartingWith(name)
                .orElseThrow(() ->
                        new IngredientsEmptyListException("Ingredients start with name = " + name + ", not found"));
    }

    public List<Ingredients> getIngredientByRemnant(int remnant) {
        return repository.getByRemnant(remnant)
                .orElseThrow(() ->
                        new IngredientsEmptyListException("Ingredients by remnant = " + remnant + ", not found"));
    }

//    public List<Ingredients> getIngredientByVegan(boolean isVegan) {
//        return repository.getByVeganIs(isVegan)
//                .orElseThrow(() ->
//                        new IngredientsEmptyListException("Ingredients by vegan = " + isVegan + ", not found"));
//    }
//
//    public List<Ingredients> getIngredientBySpicy(boolean isSpicy) {
//        return repository.getBySpicyIs(isSpicy)
//                .orElseThrow(() ->
//                        new IngredientsEmptyListException("Ingredients by spicy = " + isSpicy + ", not found"));
//    }

    @Transactional
    public Ingredients save(Ingredients ingredients) {
        if (ingredients == null) {
            throw new IngredientsNotCreatedException("Ingredient" + ingredients + " = null, is not saved");
        }
        try {
            return repository.save(ingredients);
        } catch (Exception e) {
            throw new IngredientsNotCreatedException(e.getMessage(), e);
        }
    }
}
