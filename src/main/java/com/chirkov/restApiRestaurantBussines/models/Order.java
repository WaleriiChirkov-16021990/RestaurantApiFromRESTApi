package com.chirkov.restApiRestaurantBussines.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Order_table",schema = "public")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<OrderElements> orderElements;

    @JsonIgnore
    @Fetch(value = FetchMode.SELECT)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_owner", referencedColumnName = "person_id")
    private Person owner;

    @NotNull
    @Column(name = "order_price")
    private double price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private StatusFromOrder statusFromOrder;
}
