package com.chirkov.restApiRestaurantBussines.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "Dishes", schema = "public")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dishes_id")
    private long id;

    @Column(name = "dishes_name")
    private String name;

    @Column(name = "dishes_price")
    private double price;

    @Column(name = "dishes_weight")
    private double weight;

    @Column(name = "dishes_calories")
    private int calories;

    @Column(name = "dishes_proteins")
    private int proteins;

    @Column(name = "dishes_fats")
    private int fats;

    @Column(name = "dishes_carbohydrates")
    private int carbohydrates;

    @Column(name = "dishes_image_url")
    private String imageURL;
//
//    @OneToMany(mappedBy = "dishes")
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    private List<FoodReview> foodReviewList;


    @OneToMany(mappedBy = "dishes")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<OrderElements> orderElementsIntegerMap;
//
//    @ManyToMany
//    @JoinTable(
//            name = "Dishes_Compositions_of_dishes",
//            joinColumns = @JoinColumn(name = "dishes_id"),
//            inverseJoinColumns = @JoinColumn(name = "compositions_of_dishes_id")
//    )
//    private List<CompositionsOfDishes> compositionsOfDishesList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dishes dishes)) return false;
        return Double.compare(getPrice(), dishes.getPrice()) == 0 && Double.compare(getWeight(), dishes.getWeight()) == 0 && getCalories() == dishes.getCalories() && getProteins() == dishes.getProteins() && getFats() == dishes.getFats() && getCarbohydrates() == dishes.getCarbohydrates() && Objects.equals(getName(), dishes.getName()) && Objects.equals(getImageURL(), dishes.getImageURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getWeight(), getCalories(), getProteins(), getFats(), getCarbohydrates(), getImageURL());
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}

