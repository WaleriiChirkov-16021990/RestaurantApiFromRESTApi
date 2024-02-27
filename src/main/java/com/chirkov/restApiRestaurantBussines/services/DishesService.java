package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Dishes;
import com.chirkov.restApiRestaurantBussines.repositories.DishesRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.DishesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.DishesNotFoundException;
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
        rollbackFor = {DishesNotCreatedException.class, DishesNotDeletedException.class})
public class DishesService implements DishesServiceByRepository<Dishes> {
    private final DishesRepository repository;

    @Autowired
    public DishesService(@Qualifier("dishesRepository") DishesRepository repository) {
        this.repository = repository;
    }

    public List<Dishes> findAll() {
        return repository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Dishes deleteById(Long id) throws DishesNotDeletedException {
        try {
            Dishes deletedDishes = findById(id);
            repository.deleteById(id);
            return deletedDishes;
        } catch (Exception e) {
            throw new DishesNotDeletedException("Could not delete dishes" + id +"__" + e.getMessage() , e);
        }
    }

    public Dishes findById(Long id) throws DishesNotFoundException {
        return repository.getReferenceById(id);
    }

    public Dishes findByName(String name) throws DishesNotFoundException {
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
