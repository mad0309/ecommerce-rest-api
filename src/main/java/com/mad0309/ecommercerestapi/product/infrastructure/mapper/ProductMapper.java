package com.mad0309.ecommercerestapi.product.infrastructure.mapper;

import com.mad0309.ecommercerestapi.product.domain.model.Product;
import com.mad0309.ecommercerestapi.product.infrastructure.adapter.out.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDomain(ProductEntity entity);

    ProductEntity toEntity(Product product);

    List<Product> toDomainList(List<ProductEntity> entities);
}
