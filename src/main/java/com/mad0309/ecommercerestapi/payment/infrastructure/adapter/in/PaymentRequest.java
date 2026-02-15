package com.mad0309.ecommercerestapi.payment.infrastructure.adapter.in;

import com.mad0309.ecommercerestapi.payment.domain.model.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private Long orderId;
    private PaymentMethod paymentMethod;
}
