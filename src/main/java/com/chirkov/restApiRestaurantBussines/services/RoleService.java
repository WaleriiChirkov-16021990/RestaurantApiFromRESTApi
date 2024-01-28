package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = RoleNotFoundException.class)
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    public Role getRoleById(Long id) throws RoleNotFoundException {
        Optional<Role> findRole = this.roleRepository.findById(id);
        return findRole.orElseThrow(RoleNotFoundException::new);
    }

    public Optional<Role> getRoleByName(String name) {
        return this.roleRepository.findRoleByName(name);
    }
    public Optional<Role> getRoleByRoleValue(String roleValue) throws RoleNotFoundException {
        Optional<Role> role;
        for (RoleEnum roleEnum :
             RoleEnum.values()) {
            if (roleEnum.toString().equals(roleValue)) {
                 role = this.roleRepository.findRoleByRoleValue(roleEnum);
                return role;
            }
        }
        throw new RoleNotFoundException("Role not found: " + roleValue);
    }

    @Transactional
    public void save(Role role) {
        this.roleRepository.save(role);
    }

}
