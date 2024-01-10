package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.units.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {OrderElementNotFoundException.class})
public class OrderElementsService {
}
