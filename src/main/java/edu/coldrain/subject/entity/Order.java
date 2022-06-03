package edu.coldrain.subject.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "ORDERS")
@SequenceGenerator(
        name = "ORDER_SEQ_GENERATOR",
        sequenceName = "ORDER_SEQ",
        allocationSize = 100)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ORDER_SEQ_GENERATOR")
    @Column(name = "ORDER_ID")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    // 최종 결제 금액
    private int totalPrice;

    public void calcTotalPrice(List<OrderFood> orderFoodList) {
        int sum = 0;
        for (OrderFood orderFood : orderFoodList) {
            sum += orderFood.getPrice();
        }
        sum += this.restaurant.getDeliveryFee();
        this.totalPrice = sum;
    }

    @Builder
    public Order(Restaurant restaurant, int totalPrice) {
        this.restaurant = restaurant;
        this.totalPrice = totalPrice;
    }
}
