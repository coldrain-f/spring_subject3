package edu.coldrain.subject.repository;

import edu.coldrain.subject.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
