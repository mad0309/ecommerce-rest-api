package com.mad0309.ecommercerestapi.auth.domain.port.in;

import com.mad0309.ecommercerestapi.auth.infrastructure.dto.AuthResponse;
import com.mad0309.ecommercerestapi.auth.infrastructure.dto.LoginRequest;
import com.mad0309.ecommercerestapi.auth.infrastructure.dto.RegisterRequest;

public interface AuthServicePort {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
