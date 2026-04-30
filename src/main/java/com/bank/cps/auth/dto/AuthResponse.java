package com.bank.cps.auth.dto;

import java.util.List;

public record AuthResponse(String accessToken, String refreshToken, String tokenType, String username, List<String> roles) {}
