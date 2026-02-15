package com.mad0309.ecommercerestapi.user.domain.port.in;

import com.mad0309.ecommercerestapi.user.domain.model.User;

import java.util.List;

public interface UserServicePort {

    List<User> findAll();

    User findById(Long id);

    User update(Long id, User user);

    void delete(Long id);

    User findByEmail(String email);
}
