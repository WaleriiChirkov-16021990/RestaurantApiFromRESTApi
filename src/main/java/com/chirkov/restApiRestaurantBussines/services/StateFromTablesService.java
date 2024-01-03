package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.StateFromTable;
import com.chirkov.restApiRestaurantBussines.models.StateFromTableEnum;
import com.chirkov.restApiRestaurantBussines.repositories.StateFromTablesRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.StateFromTableNotFoundException;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StateFromTablesService {
    private final StateFromTablesRepository repository;

    @Autowired
    public StateFromTablesService(StateFromTablesRepository repository) {
        this.repository = repository;
    }

    public List<StateFromTable> findAll() {
        return this.repository.findAll();
    }

    public StateFromTable getStateById(int id) {
        Optional<StateFromTable> state = this.repository.findById(id);
        return state.orElseThrow(() -> new StateFromTableNotFoundException("Not found state by Id = " + id));
    }

    public Optional<StateFromTable> getStateByName(String name) {
        return this.repository.findStateFromTablesByName(name);
    }

    public Optional<StateFromTable> getStateByValue(String stateValue) {
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

    @Transactional
    public void save(StateFromTable state) {
        this.repository.save(state);
    }
}
