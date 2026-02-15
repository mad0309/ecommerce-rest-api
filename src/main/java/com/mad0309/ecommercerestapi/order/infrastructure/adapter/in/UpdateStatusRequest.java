package com.mad0309.ecommercerestapi.order.infrastructure.adapter.in;

import com.mad0309.ecommercerestapi.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRequest {

    private OrderStatus status;
}
