package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "units_of_measurement",schema = "public")
public class UnitsOfMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "units_of_measurement_id")
    private long id;

    @Column(name = "units_of_measurement_name")
    @NotNull
    private String name;

    @Column(name = "units_of_measurement_unit")
    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumUnitsOfMeasurement unitOfMeasurement;

    @JsonBackReference
    @OneToMany(mappedBy = "units")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<CompositionsOfDishes> compositionsOfDishesList;

    @Column(name = "units_of_measurement_commentary")
    @Size(min = 4, max = 300, message = "Commentary should between 4 to 300 symbol")
    private String commentary;

}
