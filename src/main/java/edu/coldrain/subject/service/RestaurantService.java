package edu.coldrain.subject.service;

import edu.coldrain.subject.entity.Restaurant;
import edu.coldrain.subject.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public void register(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public List<Restaurant> viewAll() {
        return restaurantRepository.findAll();
    }
}
