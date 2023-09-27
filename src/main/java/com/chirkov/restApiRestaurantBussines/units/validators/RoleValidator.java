package com.chirkov.restApiRestaurantBussines.units.validators;

import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.services.RoleService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Getter
@Setter
public class RoleValidator implements Validator {
    private final RoleService roleService;

    @Autowired
    public RoleValidator(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Role.class.equals(clazz);
    }

    /**
     * @param target
     * @param errors
     */
    @SneakyThrows
    @Override
    public void validate(Object target, Errors errors) {
        Role role = (Role) target;
        if (this.roleService.getRoleByName(role.getName()).isPresent()) {
            errors.rejectValue("name","166662", "Role does already exists");
        } else if (this.roleService.getRoleByRoleValue(String.valueOf(role.getRoleValue())).isPresent()) {
            errors.rejectValue("roleValue", "166663","Role value does already exists");
        }
    }
}
