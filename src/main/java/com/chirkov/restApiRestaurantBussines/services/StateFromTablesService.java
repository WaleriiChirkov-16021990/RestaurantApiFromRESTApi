package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTableEnum;
import com.chirkov.restApiRestaurantBussines.repositories.StateFromTablesRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.StateFromTablesServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.StateFromTableNotCreateException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.StateFromTableNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.StateFromTableNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {StateFromTableNotCreateException.class, StateFromTableNotDeletedException.class})
public class StateFromTablesService implements StateFromTablesServiceByRepository<StateFromTable> {
    private final StateFromTablesRepository repository;

    @Autowired
    public StateFromTablesService(StateFromTablesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StateFromTable> findAll() {
        return this.repository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public StateFromTable deleteById(Long id) {
        StateFromTable state = findById(id);
        try {
            this.repository.deleteById(id);
            return state;
        } catch (Exception e) {
            throw new StateFromTableNotDeletedException(
                    "Error deleting state from table by id = " + id + "_\n" + e.getMessage(), e);
        }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public StateFromTable findByName(String name) {
        return this.repository.findStateFromTablesByName(name)
                .orElseThrow(() -> new StateFromTableNotFoundException("Not found state from name " + name));
    }

    @Override
    public StateFromTable findById(Long id) throws StateFromTableNotFoundException {
        Optional<StateFromTable> state = this.repository.findById(id);
        return state.orElseThrow(() -> new StateFromTableNotFoundException("Not found state by Id = " + id));
    }

    public Optional<StateFromTable> getStateByName(String name) {
        return this.repository.findStateFromTablesByName(name);
    }

    @Override
    public Optional<StateFromTable> getStateByValue(String stateValue) throws StateFromTableNotFoundException {
        Optional<StateFromTable> findState;
        for (StateFromTableEnum state :
                StateFromTableEnum.values()) {
            if (state.toString().equals(stateValue)) {
                findState = this.repository.findStateFromTablesByValue(state);
                return findState;
            }
        }
        throw new StateFromTableNotFoundException("State " + stateValue + ", not found");
    }

    @Override
    @Transactional
    public StateFromTable save(StateFromTable state) throws StateFromTableNotCreateException {
        try {
            return this.repository.save(state);
        } catch (Exception e) {
            throw new StateFromTableNotCreateException("Error from create state from table. _" + e.getMessage(), e);
        }
    }
}
