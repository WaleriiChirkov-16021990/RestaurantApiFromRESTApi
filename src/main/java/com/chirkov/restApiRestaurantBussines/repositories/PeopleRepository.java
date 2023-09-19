package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findPeopleByName(String name);

    List<Person> findPeopleByNameStartingWith(String startingWith);

    Optional<Person> findPersonByEmail(String email);

    Optional<Person> findPersonByUsername(String username);
}
