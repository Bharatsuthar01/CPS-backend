package com.bank.cps.bank.repository;

import com.bank.cps.bank.document.BankDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<BankDocument, String> {
    Optional<BankDocument> findByCode(String code);
}
