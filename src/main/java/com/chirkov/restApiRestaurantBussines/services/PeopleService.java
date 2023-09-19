package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Person;
//import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.repositories.PeopleRepository;
import com.chirkov.restApiRestaurantBussines.units.PersonNotFoundException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.SplittableRandom;

@Service
@Transactional(readOnly = true)
@Getter
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person) {
        enrichPerson(person);
        peopleRepository.save(person);
    }

    private void enrichPerson(Person person) {
//        person.getRole().setRoleValue(RoleEnum.USER);
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setUpdatedWho("John Doe");
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        enrichUpdatePerson(updatePerson);
        peopleRepository.save(updatePerson);
    }

    private void enrichUpdatePerson(Person updatePerson) {
        updatePerson.setUpdatedAt(LocalDateTime.now());
        updatePerson.setUpdatedWho("Andrey Pyatin");
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void delete(Person person) {
        peopleRepository.delete(person);
    }

    public Optional<Person> findPersonByEmail(String email) {
        return peopleRepository.findPersonByEmail(email);
    }

    public Optional<Person> findPersonByPhoneNumber(String phoneNumber) {
        return peopleRepository.findPersonByPhoneNumber(phoneNumber);
    }


}
