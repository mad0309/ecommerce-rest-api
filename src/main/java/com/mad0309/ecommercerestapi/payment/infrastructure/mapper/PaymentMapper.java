package com.mad0309.ecommercerestapi.payment.infrastructure.mapper;

import com.mad0309.ecommercerestapi.payment.domain.model.Payment;
import com.mad0309.ecommercerestapi.payment.infrastructure.adapter.out.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toDomain(PaymentEntity entity);

    PaymentEntity toEntity(Payment payment);
}
