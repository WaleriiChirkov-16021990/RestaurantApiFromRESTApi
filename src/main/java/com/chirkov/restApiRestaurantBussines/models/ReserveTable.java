package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reverse_table")
@Getter
@Setter
public class ReserveTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "internal")
    @NotNull
    private int internalDesignation;

    @NotNull
    @Column(name = "state")
    private StateFromTable state;

    public ReserveTable() {
    }
}
