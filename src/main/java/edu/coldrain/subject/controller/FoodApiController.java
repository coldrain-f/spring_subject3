package edu.coldrain.subject.controller;

import edu.coldrain.subject.entity.Food;
import edu.coldrain.subject.entity.Restaurant;
import edu.coldrain.subject.service.FoodService;
import edu.coldrain.subject.service.RestaurantService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FoodApiController {

    private final FoodService foodService;
    private final RestaurantService restaurantService;

    @ResponseStatus(HttpStatus.CREATED) // 다건 등록은 location 을 어떻게 처리?
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void register(@PathVariable Long restaurantId, @RequestBody List<FoodRegisterDTO> requestDTOs) {
        Restaurant restaurant = restaurantService.find(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음식점입니다."));

        // TODO: 2022-06-03 같은 음식점 내에서는 음식 이름이 중복될 수 없도록 구현
        final List<Food> foods = requestDTOs.stream()
                .map(f -> new Food(f.getName(), f.getPrice(), restaurant))
                .collect(Collectors.toList());

        foodService.register(foods);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDTO> viewAll(@PathVariable Long restaurantId) {
        return foodService.findAllById(restaurantId)
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

        // TODO: 2022-06-03 100원 단위로 입력하도록 구현 ( 2,220원 입력 시 에러 발생 )
        @Size(min = 100, max = 1000000, message = "허용값: 100원 ~ 1,000,000원")
        private int price;
    }
}
