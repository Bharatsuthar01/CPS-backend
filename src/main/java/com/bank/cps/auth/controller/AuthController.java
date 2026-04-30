package com.bank.cps.auth.controller;

import com.bank.cps.auth.dto.AuthResponse;
import com.bank.cps.auth.dto.LoginRequest;
import com.bank.cps.auth.dto.TokenRefreshRequest;
import com.bank.cps.auth.service.AuthService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.constants.SecurityConstants;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.ok("Login successful", CorrelationIdFilter.current(), authService.login(request));
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@Valid @RequestBody TokenRefreshRequest request) {
        return ApiResponse.ok("Token refreshed", CorrelationIdFilter.current(), authService.refresh(request));
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestHeader(value = SecurityConstants.AUTH_HEADER, required = false) String authHeader) {
        authService.logout(authHeader);
        return ApiResponse.ok("Logout successful", CorrelationIdFilter.current(), null);
    }
}
