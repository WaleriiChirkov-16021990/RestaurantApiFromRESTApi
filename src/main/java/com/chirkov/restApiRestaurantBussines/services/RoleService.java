package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import com.chirkov.restApiRestaurantBussines.repositories.RoleRepository;
import com.chirkov.restApiRestaurantBussines.units.abstractsServices.RoleServiceByRepository;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RoleNotCreatedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RoleNotDeletedException;
import com.chirkov.restApiRestaurantBussines.units.exceptions.RoleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true,
        propagation = Propagation.REQUIRED,
        rollbackFor = {RoleNotCreatedException.class, RoleNotDeletedException.class})
@AllArgsConstructor
public class RoleService implements RoleServiceByRepository<Role> {
    private final RoleRepository roleRepository;


    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Role deleteById(Long id) throws RoleNotFoundException {
        Role role = findById(id);
        try {
            this.roleRepository.deleteById(id);
            return role;
        } catch (Exception e) {
            throw new RoleNotDeletedException(
                    "Error by deleting role " + id + "_\n" + e.getMessage(), e);
        }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Role findByName(String name) throws RoleNotFoundException {
        return this.roleRepository.findRoleByName(name).orElseThrow(() -> new RoleNotFoundException("Role "));
    }

    @Override
    public Role findById(Long id) throws RoleNotFoundException {
        Optional<Role> findRole = this.roleRepository.findById(id);
        return findRole.orElseThrow(() -> new RoleNotFoundException("Role " + id + " not found"));
    }

    public Optional<Role> getRoleByNameOptional(String name) {
        return this.roleRepository.findRoleByName(name);
    }

    public Optional<Role> getRoleByRoleValue(String roleValue) throws RoleNotFoundException {
        Optional<Role> role;
        try {

            for (RoleEnum roleEnum :
                    RoleEnum.values()) {
                if (roleEnum.toString().equals(roleValue)) {
                    role = this.roleRepository.findRoleByRoleValue(roleEnum);
                    return role;
                }
            }
            throw new RoleNotFoundException("Role " + roleValue + " not found");
        } catch (Exception e) {
            throw new RoleNotFoundException(e.getMessage());
        }
    }

    @Transactional
    public Role save(Role role) throws RoleNotCreatedException {
        try {
            return this.roleRepository.save(role);
        } catch (Exception e) {
            throw new RoleNotCreatedException("Role " + role.toString() + " is not created");
        }
    }

    List<Role> getRolesByPersonName(String name) throws RoleNotFoundException {
        return this.roleRepository.findByPersonList_NameStartsWithIgnoreCase(name)
                .orElseThrow(() -> new RoleNotFoundException("Role " + name + " not found"));

    }

}
