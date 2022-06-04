package edu.coldrain.subject.controller;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.subject.entity.Order;
import edu.coldrain.subject.entity.QOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static edu.coldrain.subject.entity.QOrder.order;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderApiControllerTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory query;

    @BeforeEach
    void beforeEach() {
        query = new JPAQueryFactory(em);
    }

    @Test
    void 테스트() {
        List<Order> orders = query.select(order)
                .from(order)
                .fetch();

    }
}