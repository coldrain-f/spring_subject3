package edu.coldrain.subject.controller;

import edu.coldrain.subject.entity.Food;
import edu.coldrain.subject.entity.Restaurant;
import edu.coldrain.subject.service.FoodService;
import edu.coldrain.subject.service.RestaurantService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FoodApiController {

    private final FoodService foodService;
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void register(@PathVariable Long restaurantId, @RequestBody List<FoodRegisterDTO> requestDTOs) {
        for (FoodRegisterDTO requestDTO : requestDTOs) {
            if (requestDTO.getPrice() % 100 > 0) {
                throw new IllegalArgumentException("100원 단위로 입력해 주세요.");
            }
            if (requestDTO.getPrice() < 100 || requestDTO.getPrice() > 1000000) {
                throw new IllegalArgumentException("허용값: 100원 ~ 1,000,000원");
            }
        }

        Restaurant restaurant = restaurantService.find(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음식점입니다."));

        List<Food> foods = requestDTOs.stream()
                .map(f -> new Food(f.getName(), f.getPrice(), restaurant))
                .collect(Collectors.toList());

        foodService.register(foods);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDTO> viewAll(@PathVariable Long restaurantId) {
        return foodService.findAllByRestaurantId(restaurantId)
                .stream()
                .map(f -> new FoodResponseDTO(f.getId(), f.getName(), f.getPrice()))
                .collect(Collectors.toList());
    }

    @Data
    static class FoodResponseDTO {
        private final Long id;
        private final String name;
        private final int price;
    }

    @Data
    static class FoodRegisterDTO {
        private String name;
        private int price;
    }
}