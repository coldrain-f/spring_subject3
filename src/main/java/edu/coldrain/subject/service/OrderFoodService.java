package edu.coldrain.subject.service;

import edu.coldrain.subject.entity.OrderFood;
import edu.coldrain.subject.repository.OrderFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFoodService {

    private final OrderFoodRepository orderFoodRepository;

    public void save(OrderFood orderFood) {
        orderFoodRepository.save(orderFood);
    }
}
