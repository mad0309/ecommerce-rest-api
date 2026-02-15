package com.mad0309.ecommercerestapi.product.infrastructure.adapter.in;

import com.mad0309.ecommercerestapi.product.domain.model.Product;
import com.mad0309.ecommercerestapi.product.domain.port.in.ProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Products")
public class ProductController {

    private final ProductServicePort productServicePort;

    @GetMapping
    @Operation(summary = "Get all products", description = "Returns a paginated list of all products")
    public ResponseEntity<Page<Product>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(productServicePort.findAll(page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Returns a single product by its ID")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productServicePort.findById(id));
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get products by category", description = "Returns a list of products filtered by category")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productServicePort.findByCategory(category));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a product", description = "Creates a new product (ADMIN only)")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productServicePort.create(product));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a product", description = "Updates an existing product by its ID (ADMIN only)")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        return ResponseEntity.ok(productServicePort.update(id, product));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID (ADMIN only)")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
