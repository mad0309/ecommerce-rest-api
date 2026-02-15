package com.mad0309.ecommercerestapi.payment.infrastructure.adapter.in;

import com.mad0309.ecommercerestapi.payment.domain.model.Payment;
import com.mad0309.ecommercerestapi.payment.domain.port.in.PaymentServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Tag(name = "Payments", description = "Payment processing endpoints")
public class PaymentController {

    private final PaymentServicePort paymentServicePort;

    @PostMapping
    @Operation(summary = "Process a payment", description = "Process payment for an order")
    public ResponseEntity<Payment> processPayment(@RequestBody PaymentRequest request) {
        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .paymentMethod(request.getPaymentMethod())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentServicePort.processPayment(payment));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get payment by ID")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentServicePort.findById(id));
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Get payment by order ID")
    public ResponseEntity<Payment> getPaymentByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(paymentServicePort.findByOrderId(orderId));
    }
}
