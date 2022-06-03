package edu.coldrain.subject.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS") // ORDER 키워드라서 사용 불가능
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "ORDER_SEQ_GENERATOR",
        sequenceName = "ORDER_SEQ",
        allocationSize = 100)
public class Order {

    @Id @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ORDER_SEQ_GENERATOR")
    @Column(name = "ORDER_ID")
    private Long id;

    // 주문과 음식점은 N:1 ?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    // 주문과 음식은 1:N ?
    @OneToMany(mappedBy = "order")
    private List<Food> foods = new ArrayList<>();
}
