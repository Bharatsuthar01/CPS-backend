package com.bank.cps.auth.document;

import com.bank.cps.common.document.BaseDocument;
import java.time.Instant;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("refresh_tokens")
public class RefreshTokenDocument extends BaseDocument {
    private String username;
    @Indexed(unique = true)
    private String tokenId;
    private String tokenHash;
    @Indexed(expireAfterSeconds = 0)
    private Instant expiresAt;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getTokenId() { return tokenId; }
    public void setTokenId(String tokenId) { this.tokenId = tokenId; }
    public String getTokenHash() { return tokenHash; }
    public void setTokenHash(String tokenHash) { this.tokenHash = tokenHash; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
}
