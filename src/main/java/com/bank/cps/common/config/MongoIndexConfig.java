package com.bank.cps.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.PartialIndexFilter;
import org.springframework.data.mongodb.core.query.Criteria;

import jakarta.annotation.PostConstruct;

@Configuration
public class MongoIndexConfig {
    
    private final MongoTemplate mongoTemplate;
    
    public MongoIndexConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @PostConstruct
    public void initIndexes() {
        // Unique partial index on accountNumber + requestStatus
        mongoTemplate.indexOps("chequebook_requests").createIndex(
            new Index().on("accountNumber", Sort.Direction.ASC)
                       .on("requestStatus", Sort.Direction.ASC)
                       .unique()
                       .partial(PartialIndexFilter.of(Criteria.where("requestStatus").in("DRAFT", "PENDING_APPROVAL", "APPROVED")))
        );
        
        // Index on requestRefNo
        mongoTemplate.indexOps("chequebook_requests").createIndex(
            new Index().on("requestRefNo", Sort.Direction.ASC)
        );
        
        // TTL index on blacklisted tokens (expiresAt)
        mongoTemplate.indexOps("blacklisted_tokens").createIndex(
            new Index().on("expiresAt", Sort.Direction.ASC).expire(0)
        );
        
        // Index on username in the users collection
        mongoTemplate.indexOps("users").createIndex(
            new Index().on("username", Sort.Direction.ASC).unique()
        );
    }
}
