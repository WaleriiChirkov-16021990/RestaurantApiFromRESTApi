package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import com.chirkov.restApiRestaurantBussines.models.Person;

import java.util.List;
import java.util.Optional;

public interface PeopleServiceByRepository<M> {
    M findById(Long id);

    List<M> findAll();

    M deleteById(Long id);

    M findByName(String name);

    M save(M discount);

    M deleteByPerson(Person person);

    Optional<M> findPersonByEmail(String email);

    Optional<M> findPersonByPhoneNumber(String phoneNumber);
    M update(Long id, Person updatePerson);
}
