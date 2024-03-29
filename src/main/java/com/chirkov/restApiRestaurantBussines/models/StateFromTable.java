package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "State_from_table",schema = "public")
public class StateFromTable implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_from_table_id")
    private Long id;

    @Column(name = "state_from_table_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_from_table_value")
    @NotNull
    private StateFromTableEnum value;

    public StateFromTable(String name, StateFromTableEnum value) {
        this.name = name;
        this.value = value;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "stateFromTable")
    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<ReserveTable> reserveTable;


    public StateFromTable() {
    }
    @Override
    public String toString() {
        return "StateFromTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateFromTable state)) return false;
        return Objects.equals(getName(), state.getName()) && getValue() == state.getValue();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getValue());
    }
}
