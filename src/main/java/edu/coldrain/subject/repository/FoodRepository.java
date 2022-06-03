package edu.coldrain.subject.repository;

import edu.coldrain.subject.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByRestaurantId(Long restaurantId);
}
