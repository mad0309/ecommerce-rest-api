package com.mad0309.ecommercerestapi.order.domain.port.in;

import com.mad0309.ecommercerestapi.order.domain.model.Order;
import com.mad0309.ecommercerestapi.order.domain.model.OrderStatus;

import java.util.List;

public interface OrderServicePort {

    List<Order> findAllByUserId(Long userId);

    Order findById(Long id);

    Order create(Order order);

    Order updateStatus(Long id, OrderStatus status);
}
