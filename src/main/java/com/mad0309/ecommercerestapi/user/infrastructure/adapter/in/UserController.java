package com.mad0309.ecommercerestapi.user.infrastructure.adapter.in;

import com.mad0309.ecommercerestapi.user.domain.model.User;
import com.mad0309.ecommercerestapi.user.domain.port.in.UserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Users", description = "User management endpoints")
public class UserController {

    private final UserServicePort userServicePort;

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a list of all registered users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userServicePort.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a specific user by their ID")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userServicePort.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Updates an existing user by their ID")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userServicePort.update(id, user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Deletes a user by their ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServicePort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
