package com.bank.cps.common.security;

import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Primary
@Service
public class MongoTokenBlacklistService implements TokenBlacklistService {

    private final MongoTemplate mongoTemplate;

    public MongoTokenBlacklistService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean isBlacklisted(String token) {
        Query query = new Query(Criteria.where("jti").is(token));
        return mongoTemplate.exists(query, BlacklistedTokenDocument.class);
    }

    @Override
    public void blacklist(String token) {
        BlacklistedTokenDocument doc = new BlacklistedTokenDocument();
        doc.setId(UUID.randomUUID().toString());
        doc.setJti(token);
        // Valid tokens must adapt this TTL to their specific `exp` claim before
        // inserting for precise index TTL sweep.
        doc.setExpiresAt(Instant.now().plusSeconds(86400));
        mongoTemplate.save(doc);
    }
}
