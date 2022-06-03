package edu.coldrain.subject.controller;

import com.sun.xml.bind.v2.TODO;
import edu.coldrain.subject.dto.OrderRequestDto;
import edu.coldrain.subject.entity.Food;
import edu.coldrain.subject.entity.Order;
import edu.coldrain.subject.entity.OrderFood;
import edu.coldrain.subject.entity.Restaurant;
import edu.coldrain.subject.service.FoodService;
import edu.coldrain.subject.service.OrderFoodService;
import edu.coldrain.subject.service.OrderService;
import edu.coldrain.subject.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final RestaurantService restaurantService;
    private final FoodService foodService;
    private final OrderFoodService orderFoodService;
    private final OrderService orderService;

    @Transactional
    @PostMapping("/order/request")
    public OrderRequestDto order(@RequestBody OrderRequestDto orderRequestDto) {
        // TODO: 2022-06-04 서비스에서 처리하도록 변경하기
        // 1. 음식점의 번호로 음식점을 조회해 온다. ( 주문하려는 음식점의 이름 때문에 필요. )
        Restaurant restaurant = restaurantService.find(orderRequestDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당입니다."));

        // 주문 생성
        Order order = Order.builder().restaurant(restaurant).build();
        orderService.save(order);

        // 2. 요청받은 foods 의 음식 번호를 가지고 음식으로 변환한다.
        List<OrderFood> orderFoodList = new ArrayList<>();
        orderRequestDto.getFoods()
                .forEach(f -> {
                    Food food = foodService.findById(f.getId())
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음식입니다."));

                    OrderFood orderFood = OrderFood.builder()
                            .order(order)
                            .food(food)
                            .quantity(f.getQuantity())
                            .price(food.getPrice() * f.getQuantity())
                            .build();
                    orderFoodService.save(orderFood);
                    orderFoodList.add(orderFood);
                });

        // 최종 주문 가격 집계
        order.calcTotalPrice(orderFoodList);
        return orderRequestDto;
    }

    @GetMapping("/orders")
    public List<Order> findOrdersAll() {
        // TODO: 2022-06-04 에러 발생
        return orderService.findOrdersAll();
    }

}