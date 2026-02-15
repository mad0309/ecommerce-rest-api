package com.mad0309.ecommercerestapi.payment.domain.port.in;

import com.mad0309.ecommercerestapi.payment.domain.model.Payment;

public interface PaymentServicePort {

    Payment processPayment(Payment payment);

    Payment findById(Long id);

    Payment findByOrderId(Long orderId);
}
