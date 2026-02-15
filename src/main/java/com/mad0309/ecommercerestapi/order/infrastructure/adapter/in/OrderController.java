package com.mad0309.ecommercerestapi.order.infrastructure.adapter.in;

import com.mad0309.ecommercerestapi.order.application.service.OrderService;
import com.mad0309.ecommercerestapi.order.domain.model.Order;
import com.mad0309.ecommercerestapi.order.domain.model.OrderItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Order management endpoints")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders for authenticated user")
    public ResponseEntity<List<Order>> getAllOrders(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = orderService.resolveUserId(userDetails.getUsername());
        return ResponseEntity.ok(orderService.findAllByUserId(userId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new order")
    public ResponseEntity<Order> createOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CreateOrderRequest request) {

        Long userId = orderService.resolveUserId(userDetails.getUsername());

        List<OrderItem> items = request.getItems().stream()
                .map(itemReq -> OrderItem.builder()
                        .productId(itemReq.getProductId())
                        .quantity(itemReq.getQuantity())
                        .build())
                .collect(Collectors.toList());

        Order order = Order.builder()
                .userId(userId)
                .items(items)
                .shippingAddress(request.getShippingAddress())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(order));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update order status (ADMIN only)")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest request) {
        return ResponseEntity.ok(orderService.updateStatus(id, request.getStatus()));
    }
}
