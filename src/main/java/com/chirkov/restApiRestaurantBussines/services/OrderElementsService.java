package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Order;
import com.chirkov.restApiRestaurantBussines.models.OrderElements;
import com.chirkov.restApiRestaurantBussines.repositories.OrderElementRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.OrderElementsServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {OrderElementNotDeletedException.class, OrderElementNotCreatedException.class})
public class OrderElementsService implements OrderElementsServiceByRepository<OrderElements> {
    private final OrderElementRepository repository;

    @Autowired
    public OrderElementsService(OrderElementRepository repository) {
        this.repository = repository;
    }

    public List<OrderElements> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new OrderElementNotFoundException("Could not find all order elements" + e.getMessage(), e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public OrderElements deleteById(Long id) {
        OrderElements deleted = findById(id);
        try {
            repository.deleteById(id);
            return deleted;
        } catch (Exception e) {
            throw new OrderElementNotDeletedException("Error deleting order element" + e.getMessage(), e);
        }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public OrderElements findByName(String name) {
        return null;
    }

    public OrderElements findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new OrderElementNotFoundException("Not found orderElement by id = " + id));
    }

    public Optional<OrderElements> getOrderElementById(Long id) {
        return repository.findOrderElementsById(id);
    }

    public List<OrderElements> getOrderElementsByOrder(Order order) {
        return repository.findByOrder(order)
                .orElseThrow(() ->
                        new OrderElementNotFoundException("Not Found OrderElement for Order + " + order.getId()));
    }

    @Transactional
    public OrderElements save(OrderElements orderElements) {
        try {
            return repository.save(orderElements);
        } catch (Exception e) {
            throw new OrderElementNotCreatedException(
                    "Error saving OrderElements for Order + " + orderElements.getOrder() + ": " + e.getMessage(), e);
        }
    }
}
