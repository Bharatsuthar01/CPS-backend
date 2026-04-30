package com.bank.cps.auth.service;

import com.bank.cps.auth.document.RefreshTokenDocument;
import com.bank.cps.auth.dto.AuthResponse;
import com.bank.cps.auth.dto.LoginRequest;
import com.bank.cps.auth.dto.TokenRefreshRequest;
import com.bank.cps.auth.repository.RefreshTokenRepository;
import com.bank.cps.common.constants.SecurityConstants;
import com.bank.cps.common.exception.UnauthorizedException;
import com.bank.cps.common.security.JwtProperties;
import com.bank.cps.common.security.JwtService;
import com.bank.cps.common.security.TokenBlacklistService;
import com.bank.cps.user.document.UserDocument;
import com.bank.cps.user.repository.UserRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.HexFormat;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
                           JwtProperties jwtProperties, RefreshTokenRepository refreshTokenRepository,
                           TokenBlacklistService tokenBlacklistService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.jwtProperties = jwtProperties;
        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    public AuthResponse login(LoginRequest request) {
        UserDocument user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new UnauthorizedException("Invalid credentials");
        }
        refreshTokenRepository.deleteByUsername(user.getUsername());
        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getRoles());
        String rawRefresh = jwtService.generateRefreshToken(user.getUsername());
        RefreshTokenDocument doc = new RefreshTokenDocument();
        doc.setId(UUID.randomUUID().toString());
        doc.setUsername(user.getUsername());
        doc.setTokenId(UUID.randomUUID().toString());
        doc.setTokenHash(hash(rawRefresh));
        doc.setExpiresAt(Instant.now().plusSeconds(jwtProperties.getRefreshTokenTtlSeconds()));
        doc.setStatus("ACTIVE");
        refreshTokenRepository.save(doc);
        return new AuthResponse(accessToken, rawRefresh, "Bearer", user.getUsername(), user.getRoles());
    }

    public AuthResponse refresh(TokenRefreshRequest request) {
        String username = jwtService.getUsername(request.refreshToken());
        UserDocument user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("Invalid refresh token"));
        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getRoles());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());
        return new AuthResponse(accessToken, refreshToken, "Bearer", user.getUsername(), user.getRoles());
    }

    public void logout(String authHeader) {
        if (authHeader != null && authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            tokenBlacklistService.blacklist(authHeader.substring(SecurityConstants.TOKEN_PREFIX.length()));
        }
    }

    private String hash(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(md.digest(value.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
