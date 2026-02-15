package com.mad0309.ecommercerestapi.order.infrastructure.adapter.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private List<OrderItemRequest> items;
    private String shippingAddress;
}
