package com.mad0309.ecommercerestapi.product.domain.port.in;

import com.mad0309.ecommercerestapi.product.domain.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductServicePort {

    Page<Product> findAll(int page, int size);

    Product findById(Long id);

    List<Product> findByCategory(String category);

    Product create(Product product);

    Product update(Long id, Product product);

    void delete(Long id);
}
