package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Ingredients;
import com.chirkov.restApiRestaurantBussines.repositories.IngredientsRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.IngredientsEmptyListException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.IngredientsNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.IngredientsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED,
        rollbackFor = IngredientsNotFoundException.class)
public class IngredientsService {
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

    public Ingredients getByName(String name) {
        return repository.getByIngredientName(name)
                .orElseThrow(() ->
                        new IngredientsNotFoundException("Ingredients by name" + name + ", not found."));
    }

    public Optional<Ingredients> getOptionalByName(String name) {
        return repository.getByIngredientName(name);
    }

    public Ingredients getById(long id) {
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

    public List<Ingredients> getIngredientByVegan(boolean isVegan) {
        return repository.getByVeganIs(isVegan)
                .orElseThrow(() ->
                        new IngredientsEmptyListException("Ingredients by vegan = " + isVegan + ", not found"));
    }

    public List<Ingredients> getIngredientBySpicy(boolean isSpicy) {
        return repository.getBySpicyIs(isSpicy)
                .orElseThrow(() ->
                        new IngredientsEmptyListException("Ingredients by spicy = " + isSpicy + ", not found"));
    }

    @Transactional
    public Ingredients save(Ingredients ingredients) {
        if (ingredients == null) {
            throw new IngredientsNotCreatedException("Ingredient" + ingredients + ", not created");
        }
        try {
            return repository.save(ingredients);
        } catch (Exception e) {
            throw new IngredientsNotCreatedException(e.getMessage(), e);
        }
    }
}
