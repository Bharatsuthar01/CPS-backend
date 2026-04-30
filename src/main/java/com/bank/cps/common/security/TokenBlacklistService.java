package com.bank.cps.common.security;

public interface TokenBlacklistService {
    boolean isBlacklisted(String token);
    void blacklist(String token);
}
