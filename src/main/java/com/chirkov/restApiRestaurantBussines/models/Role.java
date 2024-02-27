package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Role",schema = "public")
@Getter
@Setter
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_value")
    @NotNull
    private RoleEnum roleValue;

    @JsonBackReference
    @ManyToMany(mappedBy = "role")
    @Fetch(FetchMode.JOIN)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<Person> personList;

    public Role() {
    }



    public Role(String name, RoleEnum roleValue) {
        this.name = name;
        this.roleValue = roleValue;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roleValue=" + roleValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return Objects.equals(getName(), role.getName()) && getRoleValue() == role.getRoleValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRoleValue());
    }
}
