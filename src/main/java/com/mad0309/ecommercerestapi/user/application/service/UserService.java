package com.mad0309.ecommercerestapi.user.application.service;

import com.mad0309.ecommercerestapi.user.domain.exception.UserNotFoundException;
import com.mad0309.ecommercerestapi.user.domain.model.User;
import com.mad0309.ecommercerestapi.user.domain.port.in.UserServicePort;
import com.mad0309.ecommercerestapi.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepositoryPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User update(Long id, User user) {
        User existingUser = userRepositoryPort.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        return userRepositoryPort.save(existingUser);
    }

    @Override
    public void delete(Long id) {
        if (userRepositoryPort.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }
        userRepositoryPort.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email", email));
    }
}
