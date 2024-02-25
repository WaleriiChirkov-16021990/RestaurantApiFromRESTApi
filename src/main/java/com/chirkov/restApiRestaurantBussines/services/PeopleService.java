package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Person;
//import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.repositories.PeopleRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.PeopleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotFoundException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.PersonNotUpdatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {PersonNotCreatedException.class, PersonNotDeletedException.class})
public class PeopleService implements PeopleServiceByRepository<Person> {
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

    public Person findById(Long id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(() -> new PersonNotFoundException("Person " + id + " not found"));
    }

    @Transactional
    public Person save(Person person) throws PersonNotCreatedException {
        try {
            enrichPerson(person);
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
            return peopleRepository.save(person);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new PersonNotCreatedException("Error saving person " + person + ": " + e.getMessage(), e);
        }
    }

    @Transactional
    public Person saveAdmin(Person person) throws PersonNotCreatedException {
        try {
            enrichPersonAdmin(person);
            person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
            return peopleRepository.save(person);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new PersonNotCreatedException("Error saving person " + person + ": " + e.getMessage(), e);
        }
    }

    private void enrichPersonAdmin(Person person) throws RoleNotFoundException {
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleService.findByName("ADMIN"));
        roles.add(this.roleService.findById(1L));
//        roles.add(this.roleService.findById(2L));
        person.setRole(roles);
        person.setDiscount(this.discountService.findById(5L));
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setUpdatedWho("Valerii Chirkov");
    }

    private void enrichPerson(Person person) throws RoleNotFoundException {
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleService.findByName("USER"));
//        roles.add(this.roleService.findById(1L));
        // TODO Auto select role from new person
        person.setRole(roles);
//        person.setRole(this.roleService.getRoleById(person.getRole().getId()));
        person.setDiscount(this.discountService.findById(1L));
        // TODO Auto select discount from new person
//        person.setDiscount(this.discountService.findById(person.getDiscount().getId()));
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setUpdatedWho("John Doe");
    }

    @Transactional
    public Person update(Long id, Person updatePerson) throws PersonNotUpdatedException {
        try {
            updatePerson.setId(id);
            enrichUpdatePerson(updatePerson);
            return peopleRepository.save(updatePerson);
        } catch (Exception e) {
            throw new PersonNotUpdatedException("Could not update person with id " + updatePerson.getId(), e);
        }
    }

    private void enrichUpdatePerson(Person updatePerson) {
        updatePerson.setUpdatedAt(LocalDateTime.now());
        updatePerson.setUpdatedWho("Andrey Pyatin");
    }

    @Transactional
    public Person deleteById(Long id) throws PersonNotDeletedException {
        Person deletePerson = findById(id);
        try {
            peopleRepository.deleteById(id);
            return deletePerson;
        } catch (Exception e) {
            throw new PersonNotDeletedException("Could not delete person with id " + id + "__\n" + e.getMessage(), e);
        }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Person findByName(String name) {
        return null;
    }

    @Transactional
    public Person deleteByPerson(Person person) {
        try {
            peopleRepository.delete(person);
            return person;
        } catch (Exception e) {
            throw new PersonNotDeletedException(
                    "Could not delete person with username " + person.getUsername() + "__\n" + e.getMessage(), e);
        }
    }

    public Optional<Person> findPersonByEmail(String email) {
        return peopleRepository.findPersonByEmail(email);
    }

    public Optional<Person> findPersonByPhoneNumber(String phoneNumber) {
        return peopleRepository.findPersonByPhoneNumber(phoneNumber);
    }

    public Date getStartYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1958);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 6);
        return calendar.getTime();
    }

    public Date getEndYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return calendar.getTime();
    }


}