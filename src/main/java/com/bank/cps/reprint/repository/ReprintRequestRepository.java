package com.bank.cps.reprint.repository;

import com.bank.cps.reprint.document.ReprintRequestDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReprintRequestRepository extends MongoRepository<ReprintRequestDocument, String> {
    Optional<ReprintRequestDocument> findByCode(String code);
}
