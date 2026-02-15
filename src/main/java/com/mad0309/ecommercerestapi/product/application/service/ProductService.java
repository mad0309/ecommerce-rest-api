package com.mad0309.ecommercerestapi.product.application.service;

import com.mad0309.ecommercerestapi.product.domain.exception.ProductNotFoundException;
import com.mad0309.ecommercerestapi.product.domain.model.Product;
import com.mad0309.ecommercerestapi.product.domain.port.in.ProductServicePort;
import com.mad0309.ecommercerestapi.product.domain.port.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService implements ProductServicePort {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(int page, int size) {
        return productRepositoryPort.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepositoryPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategory(String category) {
        return productRepositoryPort.findByCategory(category);
    }

    @Override
    public Product create(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepositoryPort.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product existingProduct = productRepositoryPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImageUrl(product.getImageUrl());

        return productRepositoryPort.save(existingProduct);
    }

    @Override
    public void delete(Long id) {
        if (productRepositoryPort.findById(id).isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        productRepositoryPort.deleteById(id);
    }
}
