package com.bank.cps.common.security;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("blacklisted_tokens")
public class BlacklistedTokenDocument {
    private String id;
    private String jti;
    private Instant expiresAt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getJti() { return jti; }
    public void setJti(String jti) { this.jti = jti; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
}
