package com.bank.cps.auth.service;

import com.bank.cps.auth.dto.AuthResponse;
import com.bank.cps.auth.dto.LoginRequest;
import com.bank.cps.auth.dto.TokenRefreshRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse refresh(TokenRefreshRequest request);
    void logout(String authHeader);
}
