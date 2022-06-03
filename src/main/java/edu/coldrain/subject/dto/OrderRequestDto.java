package edu.coldrain.subject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequestDto {

    // 주문하는 음식점 번호
    private Long restaurantId;

    // 주문하는 음식 리스트
    private List<OrderFoodRequestDto> foods = new ArrayList<>();

    @Builder
    public OrderRequestDto(Long restaurantId, List<OrderFoodRequestDto> foods) {
        this.restaurantId = restaurantId;
        this.foods = foods;
    }
}
