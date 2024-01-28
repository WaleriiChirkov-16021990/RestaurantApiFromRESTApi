package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Person;
//import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.repositories.PeopleRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = PersonNotFoundException.class)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final DiscountService discountService;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder bCryptPasswordEncoder, RoleService roleService, DiscountService discountService) {
        this.peopleRepository = peopleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
        this.discountService = discountService;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(Long id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(() -> new PersonNotFoundException("Person " + id + " not found"));
    }

    @Transactional
    public Person save(Person person) {
        try {
            enrichPerson(person);
        } catch (RoleNotFoundException e) {
            e.printStackTrace();
        }
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        return peopleRepository.save(person);
    }

    private void enrichPerson(Person person) throws RoleNotFoundException {
//        person.setRole(this.roleService.getRoleByName("youngUser"));
        // TODO Auto select role from person
        person.setRole(this.roleService.getRoleById(1L));
//        person.setRole(this.roleService.getRoleById(person.getRole().getId()));
        person.setDiscount(this.discountService.findById(1L));
        // TODO Auto select discount from new person
//        person.setDiscount(this.discountService.findById(person.getDiscount().getId()));
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setUpdatedWho("John Doe");
    }

    @Transactional
    public Person update(Long id, Person updatePerson) {
        updatePerson.setId(id);
        enrichUpdatePerson(updatePerson);
        return peopleRepository.save(updatePerson);
    }

    private void enrichUpdatePerson(Person updatePerson) {
        updatePerson.setUpdatedAt(LocalDateTime.now());
        updatePerson.setUpdatedWho("Andrey Pyatin");
    }

    @Transactional
    public void delete(Long id) { peopleRepository.deleteById(id);
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
