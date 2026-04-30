package com.bank.cps.integration.repository;

import com.bank.cps.integration.document.IntegrationJobDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IntegrationJobRepository extends MongoRepository<IntegrationJobDocument, String> {
    Optional<IntegrationJobDocument> findByCode(String code);
}
