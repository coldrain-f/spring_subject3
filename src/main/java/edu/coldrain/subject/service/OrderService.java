package edu.coldrain.subject.service;

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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderFoodRepository orderFoodRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public Order order() {
        return null;
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findOrdersAll() {
        return orderRepository.findOrdersAll();
    }
}
