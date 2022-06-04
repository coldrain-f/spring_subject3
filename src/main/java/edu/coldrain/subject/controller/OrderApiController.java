package edu.coldrain.subject.controller;

import com.sun.xml.bind.v2.TODO;
import edu.coldrain.subject.dto.OrderFoodResponseDto;
import edu.coldrain.subject.dto.OrderRequestDto;
import edu.coldrain.subject.dto.OrderResponseDto;
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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/order/request")
    public OrderResponseDto order(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.order(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> findAll() {
        List<Order> orders = orderService.findAll();
        List<OrderResponseDto> responses = new ArrayList<>();
        for (Order order : orders) {
            OrderResponseDto response = orderService.findOrderResponseById(order.getId());
            responses.add(response);
        }
        return responses;
    }

}