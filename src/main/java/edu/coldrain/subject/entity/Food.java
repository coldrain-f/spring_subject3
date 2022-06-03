package edu.coldrain.subject.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@SequenceGenerator(
        name = "FOOD_SEQ_GENERATOR",
        sequenceName = "FOOD_SEQ",
        allocationSize = 100)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "FOOD_SEQ_GENERATOR")
    @Column(name = "FOOD_ID")
    private Long id;

    private String name; // 음식명

    private int price; // 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    // 음식과 주문은 N:1 ?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Builder
    public Food(String name, int price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }
}
