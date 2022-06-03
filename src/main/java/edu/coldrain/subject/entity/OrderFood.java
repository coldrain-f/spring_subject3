package edu.coldrain.subject.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@SequenceGenerator(
        name = "ORDER_FOOD_SEQ_GENERATOR",
        sequenceName = "ORDER_FOOD_SEQ",
        allocationSize = 100)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderFood {

    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ORDER_FOOD_SEQ_GENERATOR")
    private Long id;

    // 개수
    private int quantity;

    // 가격
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOOD_ID")
    private Food food;

    @Builder
    public OrderFood(int quantity, int price, Order order, Food food) {
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.food = food;
    }
}
