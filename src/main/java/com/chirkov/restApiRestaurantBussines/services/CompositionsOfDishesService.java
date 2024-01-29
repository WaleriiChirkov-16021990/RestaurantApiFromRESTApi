package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.CompositionsOfDishesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.repositories.CompositionsOfDishesRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.CompositionsOfDishesEmptyListException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.CompositionsOfDishesNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.CompositionsOfDishesNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.CompositionsOfDishesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {CompositionsOfDishesNotCreatedException.class,CompositionsOfDishesNotDeletedException.class})
@Qualifier
public class CompositionsOfDishesService implements CompositionsOfDishesServiceByRepository<CompositionsOfDishes> {
    private final CompositionsOfDishesRepository repository;

    @Autowired
    public CompositionsOfDishesService(CompositionsOfDishesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CompositionsOfDishes> findAll() throws CompositionsOfDishesEmptyListException {
        List<CompositionsOfDishes> result;
        try {
            result = repository.findAll();
        } catch (Exception e) {
            throw new CompositionsOfDishesEmptyListException(e.getMessage(), e);
        }
        if (result.isEmpty()) {
            throw new CompositionsOfDishesEmptyListException("No compositions were found for this service");
        }
        return result;
    }

    @Override
    public CompositionsOfDishes findById(Long id) throws CompositionsOfDishesNotFoundException {
        return repository.findById(id)
                .orElseThrow(() ->
                        new CompositionsOfDishesNotFoundException("CompositionsOfDishes not found for id " + id));
    }


    @Override
    public CompositionsOfDishes findByName(String name) throws CompositionsOfDishesNotFoundException {
        return repository.findCompositionsOfDishesByName(name)
                .orElseThrow(() ->
                        new CompositionsOfDishesNotFoundException("CompositionsOfDishes not found for name " + name));
    }

    public Optional<CompositionsOfDishes> getByNameOptional(String name) throws CompositionsOfDishesNotFoundException {
        return repository.findCompositionsOfDishesByName(name);
    }

    @Transactional
    public CompositionsOfDishes save(CompositionsOfDishes compositions) throws CompositionsOfDishesNotCreatedException {
        if (compositions == null) {
            throw new CompositionsOfDishesNotCreatedException("Null CompositionsOfDishes not created");
        }
        try {
            return repository.save(compositions);
        } catch (Exception e) {
            throw new CompositionsOfDishesNotCreatedException(e.getMessage(), e);
        }
    }

    @Transactional
    public CompositionsOfDishes remove(CompositionsOfDishes composition) throws CompositionsOfDishesNotDeletedException {
        if (composition == null) {
            throw new CompositionsOfDishesNotDeletedException("Null CompositionsOfDishes not found");
        }
        try {
            repository.delete(composition);
            return composition;
        } catch (Exception e) {
            throw new CompositionsOfDishesNotDeletedException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public CompositionsOfDishes deleteById(Long id) {
        try {
            CompositionsOfDishes compositions = findById(id);
            repository.deleteById(id);
            return compositions;
        } catch (Exception e) {
            throw new CompositionsOfDishesNotDeletedException(e.getMessage(), e);
        }
    }

}
