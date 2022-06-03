package edu.coldrain.subject.repository;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.subject.entity.*;

import javax.persistence.EntityManager;
import java.util.List;

import static edu.coldrain.subject.entity.QFood.food;
import static edu.coldrain.subject.entity.QOrder.order;
import static edu.coldrain.subject.entity.QOrderFood.orderFood;
import static edu.coldrain.subject.entity.QRestaurant.restaurant;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory query;

    public OrderRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Order> findOrdersAll() {
        return query.select(order)
                .from(order)
                .join(order.restaurant, restaurant).fetchJoin()
                //.join(order, orderFood.order)
                //.join(orderFood.food, food)
                .fetch();
    }
}
