package edu.coldrain.subject.service;

import edu.coldrain.subject.entity.Food;
import edu.coldrain.subject.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    // SimpleJpaRepository 에 이미 트랜잭션 걸려있음
    public void register(List<Food> foods) {
        foodRepository.saveAll(foods);
    }

    public List<Food> findAllByRestaurantId(Long id) {
        return foodRepository.findAllByRestaurantId(id);
    }
}
