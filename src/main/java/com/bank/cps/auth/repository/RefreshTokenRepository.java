package com.bank.cps.auth.repository;

import com.bank.cps.auth.document.RefreshTokenDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepository extends MongoRepository<RefreshTokenDocument, String> {
    Optional<RefreshTokenDocument> findByTokenId(String tokenId);
    void deleteByUsername(String username);
}
