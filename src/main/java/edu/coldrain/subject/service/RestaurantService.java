package edu.coldrain.subject.service;

import edu.coldrain.subject.entity.Restaurant;
import edu.coldrain.subject.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public void register(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> find(Long id) {
        return restaurantRepository.findById(id);
    }
}
