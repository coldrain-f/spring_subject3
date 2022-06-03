package edu.coldrain.subject.controller;

import edu.coldrain.subject.entity.Restaurant;
import edu.coldrain.subject.service.RestaurantService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RestaurantApiController {

    private final RestaurantService restaurantService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/restaurant/register")
    public void register(@RequestBody RestaurantRegisterDTO requestDTO) {
        Restaurant restaurant = Restaurant.builder()
                .name(requestDTO.getName())
                .minOrderPrice(requestDTO.getMinOrderPrice())
                .deliveryFee(requestDTO.getDeliveryFee())
                .build();

        restaurantService.register(restaurant);
    }

    @GetMapping("/restaurants")
    public List<RestaurantResponseDTO> viewAll() {
        return restaurantService.viewAll().stream()
                .map(r -> new RestaurantResponseDTO(r.getId(), r.getName(), r.getMinOrderPrice(), r.getDeliveryFee()))
                .collect(Collectors.toList());
    }

    @Data
    static class RestaurantResponseDTO {
        private final Long id;
        private final String name;
        private final int minOrderPrice;
        private final int deliveryFee;
    }

    @Data
    static class RestaurantRegisterDTO {
        private String name;

        // TODO: 2022-06-03 100원 단위로 입력하도록 구현하기 ( 2,220원은 에러 발생 )
        @Size(min = 1000, max = 100000, message = "허용값: 1,000원 ~ 100,000원")
        private int minOrderPrice;

        // TODO: 2022-06-03 500원 단위로 입력하도록 구현하기
        @Size(min = 0, max = 10000, message = "허용값: 0원 ~ 10,000원")
        private int deliveryFee;
    }
}
