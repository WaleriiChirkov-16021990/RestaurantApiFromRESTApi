package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Discount",schema = "public")
@Getter
@Setter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private int id;

    @Column(name = "discount_name")
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "discount_sale")
    @NotNull
    private DiscountEnum sale;

    @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL)
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Person> personList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount discount)) return false;
        return Objects.equals(getName(), discount.getName()) && getSale() == discount.getSale();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSale());
    }
}
