package edu.coldrain.subject.controller;

import edu.coldrain.subject.entity.Restaurant;
import edu.coldrain.subject.service.RestaurantService;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RestaurantApiController {

    private final RestaurantService restaurantService;

    // TODO: 2022-06-03 DTO 로 변환해서 응답하도록 변경하기
    @PostMapping("/restaurant/register")
    public Restaurant register(@RequestBody @Valid RestaurantRegisterDTO requestDTO) {
        if (requestDTO.getMinOrderPrice() % 100 > 0) {
            throw new IllegalArgumentException("100원 단위로 입력해 주세요.");
        }
        if ((requestDTO.getDeliveryFee() % 1000) % 500 != 0) {
            throw new IllegalArgumentException("500원 단위로 입력해 주세요.");
        }

        Restaurant restaurant = Restaurant.builder()
                .name(requestDTO.getName())
                .minOrderPrice(requestDTO.getMinOrderPrice())
                .deliveryFee(requestDTO.getDeliveryFee())
                .build();

        return restaurantService.register(restaurant);
    }

    @GetMapping("/restaurants")
    public List<RestaurantResponseDTO> viewAll() {
        return restaurantService.findAll().stream()
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

        @Range(min = 1000, max = 100000, message = "허용값: 1,000원 ~ 100,000원")
        private int minOrderPrice;

        @Range(min = 0, max = 10000, message = "허용값: 0원 ~ 10,000원")
        private int deliveryFee;
    }
}
