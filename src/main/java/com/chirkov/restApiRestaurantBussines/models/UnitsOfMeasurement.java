//package com.chirkov.restApiRestaurantBussines.models;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.Cascade;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "units_of_measurement")
//public class UnitsOfMeasurement {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "units_of_measurement_id")
//    private int id;
//
//    @Column(name = "units_of_measurement_name")
//    @NotNull
//    private String name;
//
//    @Column(name = "units_of_measurement_unit")
//    @NotNull
//    private EnumUnitsOfMeasurement unitOfMeasurement;
//
//    @OneToMany(mappedBy = "units")
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    private List<CompositionsOfDishes> compositionsOfDishesList;
//
//    @Column(name = "units_of_measurement_commentary")
//    @Size(min = 4, max = 300, message = "Commentary should between 4 to 300 symbol")
//    private String commentary;
//
//    public UnitsOfMeasurement() {
//
//    }
//
//}
