package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "compositions_of_dishes",schema = "public")
public class CompositionsOfDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compositions_of_dishes_id")
    private Long id;

    @Column(name = "compositions_of_dishes_name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "compositionsOfDishesList")
    private List<Dishes> dishesList;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compositions_of_dishes_ingredient_id", referencedColumnName = "ingredient_id")
    @NotNull
    private Ingredients ingredient;

    @Column(name = "compositions_of_dishes_count_of_units")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compositions_of_dishes_units", referencedColumnName = "units_of_measurement_id")
    @NotNull
    private UnitsOfMeasurement units;

    public CompositionsOfDishes() {
    }
}
