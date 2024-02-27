package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.CompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.models.DishesCompositionsOfDishes;
import com.chirkov.restApiRestaurantBussines.repositories.DishesCompositionsOfDishesRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.DishesCompositionsOfDishesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesCompositionsOfDishesNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesCompositionsOfDishesNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {DishesCompositionsOfDishesNotCreatedException.class,
                DishesCompositionsOfDishesNotDeletedException.class})
public class DishesCompositionsOfDishesService implements DishesCompositionsOfDishesServiceByRepository<DishesCompositionsOfDishes> {
    private final DishesCompositionsOfDishesRepository repository;

    @Autowired
    public DishesCompositionsOfDishesService(DishesCompositionsOfDishesRepository repository) {
        this.repository = repository;
    }

    /**
     * @param compositions
     * @return
     */
    @Override
    public Optional<List<DishesCompositionsOfDishes>> findByCompositionsOfDishes(CompositionsOfDishes compositions) {
        return Optional.empty();
    }

    /**
     * @param dishes
     * @return
     */
    @Override
    public Optional<List<DishesCompositionsOfDishes>> findByDishes(Dishes dishes) {
        return Optional.empty();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public DishesCompositionsOfDishes findById(Long id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<DishesCompositionsOfDishes> findAll() {
        return null;

    }

    /**
     * @param id
     * @return
     */
    @Transactional
    @Override
    public DishesCompositionsOfDishes deleteById(Long id) {
        // todo: remove
        return null;
    }

    /**
     * @param discount
     * @return
     */
    @Override
    @Transactional
    public DishesCompositionsOfDishes save(DishesCompositionsOfDishes discount) {
        return repository.save(discount);
    }
}
