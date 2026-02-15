package com.mad0309.ecommercerestapi.order.application.service;

import com.mad0309.ecommercerestapi.order.domain.exception.OrderNotFoundException;
import com.mad0309.ecommercerestapi.order.domain.model.Order;
import com.mad0309.ecommercerestapi.order.domain.model.OrderItem;
import com.mad0309.ecommercerestapi.order.domain.model.OrderStatus;
import com.mad0309.ecommercerestapi.order.domain.port.in.OrderServicePort;
import com.mad0309.ecommercerestapi.order.domain.port.out.OrderRepositoryPort;
import com.mad0309.ecommercerestapi.product.domain.exception.ProductNotFoundException;
import com.mad0309.ecommercerestapi.product.domain.model.Product;
import com.mad0309.ecommercerestapi.product.domain.port.out.ProductRepositoryPort;
import com.mad0309.ecommercerestapi.user.domain.exception.UserNotFoundException;
import com.mad0309.ecommercerestapi.user.domain.model.User;
import com.mad0309.ecommercerestapi.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort orderRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return orderRepositoryPort.findAllByUserId(userId);
    }

    @Override
    public Order findById(Long id) {
        return orderRepositoryPort.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    @Override
    public Order create(Order order) {
        for (OrderItem item : order.getItems()) {
            Product product = productRepositoryPort.findById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(
                            "Product not found with id: " + item.getProductId()));

            item.setProductName(product.getName());
            item.setUnitPrice(product.getPrice());
            item.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        BigDecimal totalAmount = order.getItems().stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        return orderRepositoryPort.save(order);
    }

    @Override
    public Order updateStatus(Long id, OrderStatus status) {
        Order order = orderRepositoryPort.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepositoryPort.save(order);
    }

    public Long resolveUserId(String email) {
        User user = userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return user.getId();
    }
}
