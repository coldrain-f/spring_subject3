package edu.coldrain.subject.dto;

import edu.coldrain.subject.controller.OrderApiController;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {

    private String restaurantName;
    private List<OrderFoodResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;

    @Builder
    public OrderResponseDto(String restaurantName, List<OrderFoodResponseDto> foods, int deliveryFee, int totalPrice) {
        this.restaurantName = restaurantName;
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
