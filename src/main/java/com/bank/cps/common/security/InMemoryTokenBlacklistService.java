package com.bank.cps.common.security;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class InMemoryTokenBlacklistService implements TokenBlacklistService {
    private final Set<String> tokens = ConcurrentHashMap.newKeySet();
    public boolean isBlacklisted(String token) { return tokens.contains(token); }
    public void blacklist(String token) { tokens.add(token); }
}
