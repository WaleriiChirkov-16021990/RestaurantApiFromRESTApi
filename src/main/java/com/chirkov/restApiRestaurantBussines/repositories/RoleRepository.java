package com.chirkov.restApiRestaurantBussines.repositories;

import com.chirkov.restApiRestaurantBussines.models.Role;
import com.chirkov.restApiRestaurantBussines.models.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("""
            select r from Role r inner join r.personList personList
            where upper(personList.name) like upper(concat(?1, '%'))""")
    Optional<List<Role>> findByPersonList_NameStartsWithIgnoreCase(@Nullable String name);

    Optional<Role> findRoleByName(String roleName);

    Optional<Role> findRoleByRoleValue(@NotNull RoleEnum roleValue);



}
