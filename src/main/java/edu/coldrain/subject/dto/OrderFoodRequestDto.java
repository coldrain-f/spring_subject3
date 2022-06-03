package edu.coldrain.subject.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class OrderFoodRequestDto {

    // 주문하는 음식 번호
    private Long id;

    // 주문하는 음식의 수량
    private int quantity;

    @Builder
    public OrderFoodRequestDto(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
