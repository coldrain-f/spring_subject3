package edu.coldrain.subject.service;

import edu.coldrain.subject.dto.OrderFoodResponseDto;
import edu.coldrain.subject.dto.OrderRequestDto;
import edu.coldrain.subject.dto.OrderResponseDto;
import edu.coldrain.subject.entity.Food;
import edu.coldrain.subject.entity.Order;
import edu.coldrain.subject.entity.OrderFood;
import edu.coldrain.subject.entity.Restaurant;
import edu.coldrain.subject.repository.FoodRepository;
import edu.coldrain.subject.repository.OrderFoodRepository;
import edu.coldrain.subject.repository.OrderRepository;
import edu.coldrain.subject.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderFoodRepository orderFoodRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    // 서비스에서 다른 서비스 사용해도 되나?
    private final RestaurantService restaurantService;
    private final FoodService foodService;
    private final OrderFoodService orderFoodService;

    @Transactional // 이게 없으면 totalCount 에 값이 설정 안 됨
    public OrderResponseDto order(OrderRequestDto orderRequestDto) {
        // 1. 음식점의 번호로 음식점을 조회해 온다. ( 주문하려는 음식점의 이름 때문에 필요. )
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당입니다."));

        // 주문 생성
        Order order = Order.builder().restaurant(restaurant).build();
        orderRepository.save(order);

        // 2. 요청받은 foods 의 음식 번호를 가지고 음식으로 변환한다.
        List<OrderFood> orderFoodList = new ArrayList<>();

        orderRequestDto.getFoods()
                .forEach(f -> {
                    Food food = foodRepository.findById(f.getId())
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음식입니다."));

                    OrderFood orderFood = OrderFood.builder()
                            .order(order)
                            .food(food)
                            .quantity(f.getQuantity())
                            .price(food.getPrice() * f.getQuantity())
                            .build();

                    orderFoodRepository.save(orderFood);
                    orderFoodList.add(orderFood);
                });

        // 최종 주문 가격 집계
        order.calcTotalPrice(orderFoodList);

        List<OrderFoodResponseDto> foods = orderFoodList.stream()
                .map(of ->
                        OrderFoodResponseDto.builder()
                                .name(of.getFood().getName())
                                .price(of.getPrice())
                                .quantity(of.getQuantity())
                                .build()
                ).collect(Collectors.toList());
        return OrderResponseDto.builder()
                .restaurantName(order.getRestaurant().getName())
                .deliveryFee(order.getRestaurant().getDeliveryFee())
                .foods(foods)
                .build();
    }

    @Transactional
    public OrderResponseDto findOrderResponseById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

        List<OrderFoodResponseDto> foods = toOrderFoodResponses(order);

        return OrderResponseDto.builder()
                .restaurantName(order.getRestaurant().getName())
                .foods(foods)
                .deliveryFee(order.getRestaurant().getDeliveryFee())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    // 주문의 주문음식 목록을 주문음식 목록 DTO 로 변환
    private List<OrderFoodResponseDto> toOrderFoodResponses(Order order) {
        return order.getOrderFoods().stream()
                .map(of ->
                        OrderFoodResponseDto.builder()
                                .name(of.getFood().getName())
                                .quantity(of.getQuantity())
                                .price(of.getPrice())
                                .build()
                ).collect(Collectors.toList());
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
