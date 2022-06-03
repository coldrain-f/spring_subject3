package edu.coldrain.subject;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.coldrain.subject.entity.*;
import edu.coldrain.subject.repository.FoodRepository;
import edu.coldrain.subject.repository.OrderFoodRepository;
import edu.coldrain.subject.repository.OrderRepository;
import edu.coldrain.subject.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

import static edu.coldrain.subject.entity.QFood.food;
import static edu.coldrain.subject.entity.QOrder.order;
import static edu.coldrain.subject.entity.QOrderFood.orderFood;
import static edu.coldrain.subject.entity.QRestaurant.restaurant;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SpringSubject3ApplicationTests {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderFoodRepository orderFoodRepository;

    private JPAQueryFactory query;

    @BeforeEach
    void beforeEach() {
        query = new JPAQueryFactory(em);

        // 음식점 등록
        Restaurant restaurant = Restaurant.builder()
                .name("쉐이크쉑 청담점")
                .minOrderPrice(5000)
                .deliveryFee(2000)
                .build();

        restaurantRepository.save(restaurant);

        // 음식점에 음식 등록
        Food food1 = new Food("쉑버거 더블", 10900, restaurant);
        Food food2 = new Food("치즈 감자튀김", 4900, restaurant);
        Food food3 = new Food("쉐이크", 5900, restaurant);

        foodRepository.save(food1);
        foodRepository.save(food2);
        foodRepository.save(food3);

        // "쉐이크쉑 청담점"에서 음식 주문
    }

    @Test
    void contextLoads() {


    }

}
