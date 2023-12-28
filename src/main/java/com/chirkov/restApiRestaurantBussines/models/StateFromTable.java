package com.chirkov.restApiRestaurantBussines.models;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "StateFromTable")
public class StateFromTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_from_table_id")
    private int id;

    @Column(name = "state_from_table_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_from_table_value")
    @NotNull
    private StateFromTableEnum value;

    @OneToMany(mappedBy = "stateFromTable")
    @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<ReserveTable> reserveTable;
}
