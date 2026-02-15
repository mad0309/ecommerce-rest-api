package com.mad0309.ecommercerestapi.payment.domain.exception;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(Long id) {
        super("Payment not found with id: " + id);
    }

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
