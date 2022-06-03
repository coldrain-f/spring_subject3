package edu.coldrain.subject.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@SequenceGenerator(
        name = "RESTAURANT_SEQ_GENERATOR",
        sequenceName = "RESTAURANT_SEQ",
        allocationSize = 100)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue
    @Column(name = "RESTAURANT_ID")
    private Long id;

    // 음식점 이름
    private String name;

    // 최소 주문 가격
    private int minOrderPrice;

    // 기본 배달비
    private int deliveryFee;

    @Builder
    public Restaurant(String name, int minOrderPrice, int deliveryFee) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }
}
