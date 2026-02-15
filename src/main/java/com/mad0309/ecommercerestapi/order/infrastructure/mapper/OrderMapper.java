package com.mad0309.ecommercerestapi.order.infrastructure.mapper;

import com.mad0309.ecommercerestapi.order.domain.model.Order;
import com.mad0309.ecommercerestapi.order.domain.model.OrderItem;
import com.mad0309.ecommercerestapi.order.infrastructure.adapter.out.OrderEntity;
import com.mad0309.ecommercerestapi.order.infrastructure.adapter.out.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "order", ignore = true)
    OrderItemEntity toEntity(OrderItem item);

    OrderItem toDomain(OrderItemEntity entity);

    Order toDomain(OrderEntity entity);

    @Mapping(target = "items", source = "items")
    OrderEntity toEntity(Order order);

    List<Order> toDomainList(List<OrderEntity> entities);

    List<OrderItem> toDomainItemList(List<OrderItemEntity> entities);

    List<OrderItemEntity> toEntityItemList(List<OrderItem> items);
}
