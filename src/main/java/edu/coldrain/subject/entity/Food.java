package edu.coldrain.subject.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "FOOD_UNIQUE_CONSTRAINT",
                        columnNames = {"name", "restaurant_id"})}
)
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

    // 음식명
    private String name;

    // 가격
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @Builder
    public Food(String name, int price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }
}
