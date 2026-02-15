package com.mad0309.ecommercerestapi.payment.application.service;

import com.mad0309.ecommercerestapi.order.domain.exception.OrderNotFoundException;
import com.mad0309.ecommercerestapi.order.domain.model.Order;
import com.mad0309.ecommercerestapi.order.domain.model.OrderStatus;
import com.mad0309.ecommercerestapi.order.domain.port.out.OrderRepositoryPort;
import com.mad0309.ecommercerestapi.payment.domain.exception.PaymentNotFoundException;
import com.mad0309.ecommercerestapi.payment.domain.model.Payment;
import com.mad0309.ecommercerestapi.payment.domain.model.PaymentStatus;
import com.mad0309.ecommercerestapi.payment.domain.port.in.PaymentServicePort;
import com.mad0309.ecommercerestapi.payment.domain.port.out.PaymentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService implements PaymentServicePort {

    private final PaymentRepositoryPort paymentRepositoryPort;
    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public Payment processPayment(Payment payment) {
        Order order = orderRepositoryPort.findById(payment.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + payment.getOrderId()));

        payment.setAmount(order.getTotalAmount());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setCreatedAt(LocalDateTime.now());

        Payment savedPayment = paymentRepositoryPort.save(payment);

        order.setStatus(OrderStatus.CONFIRMED);
        orderRepositoryPort.save(order);

        return savedPayment;
    }

    @Override
    @Transactional(readOnly = true)
    public Payment findById(Long id) {
        return paymentRepositoryPort.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Payment findByOrderId(Long orderId) {
        return paymentRepositoryPort.findByOrderId(orderId)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found for order id: " + orderId));
    }
}
