package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Cascade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "units_of_measurement",schema = "public")
public class UnitsOfMeasurement implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
