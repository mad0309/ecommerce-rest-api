package com.mad0309.ecommercerestapi.order.infrastructure.adapter.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    private Long productId;
    private Integer quantity;
}
