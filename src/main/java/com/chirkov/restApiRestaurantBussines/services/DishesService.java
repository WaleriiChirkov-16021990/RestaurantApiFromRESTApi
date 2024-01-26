package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.repositories.DishesRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {DishesNotFoundException.class})
public class DishesService {
    private final DishesRepository repository;

    @Autowired
    public DishesService(DishesRepository repository) {
        this.repository = repository;
    }

    public List<Dishes> findAllDishes() {
        return repository.findAll();
    }

    public Dishes getDishesById(long id) throws DishesNotFoundException {
        return repository.getReferenceById(id);
    }

    public Dishes getDishesByName(String name) throws DishesNotFoundException {
        return repository.findByName(name).orElseThrow(() -> new DishesNotFoundException("Dishes by name " + name + " not found"));
    }

    public Optional<Dishes> getDishesByNameOpt(String name) {
        return repository.findByName(name);
    }

    public List<Dishes> getDishesByStartingWith(String start) throws DishesNotFoundException {
        return repository.findByNameStartingWith(start).orElseThrow(() -> new DishesNotFoundException("Dishes name by " + start + " starting not found"));
    }

    // TODO Create a better implementation of getDishes

    @Transactional
    public Dishes save(Dishes dishes) {
        return repository.save(dishes);
    }
}
