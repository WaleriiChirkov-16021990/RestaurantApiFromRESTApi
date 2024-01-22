package com.chirkov.restApiRestaurantBussines.models;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "ingredients",schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private int id;

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
//
//    @OneToMany(mappedBy = "ingredient")
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    private List<CompositionsOfDishes> compositionsOfDishesList;

}
