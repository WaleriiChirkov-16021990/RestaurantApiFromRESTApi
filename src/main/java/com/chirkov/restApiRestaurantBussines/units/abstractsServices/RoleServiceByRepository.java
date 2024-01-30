package com.chirkov.restApiRestaurantBussines.units.abstractsServices;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface RoleServiceByRepository<M> {
    M findById(Long id) throws RoleNotFoundException;

    List<M> findAll();

    M deleteById(Long id) throws RoleNotFoundException;

    M findByName(String name) throws RoleNotFoundException;

    M save(M discount);
}
