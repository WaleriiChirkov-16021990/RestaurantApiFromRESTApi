package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ingredients",schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @Column(name = "ingredient_name")
    @NotNull
    private String ingredientName;

    @Column(name = "ingredient_remnant_number")
    @NotNull
    @Range(min = 0, message = "Remnant should be zero or bigger")
    private int remnant;

    @Column(name = "ingredient_description")
    private String description;

    @Column(name = "ingredient_is_vegan")
    @NotNull
    private boolean isVegan;

    @Column(name = "ingredient_is_spicy")
    @NotNull
    private boolean isSpicy;

    @JsonIgnore
    @OneToMany(mappedBy = "ingredient")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<CompositionsOfDishes> compositionsOfDishesList;

}
