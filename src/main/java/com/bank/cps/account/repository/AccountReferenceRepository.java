package com.bank.cps.account.repository;

import com.bank.cps.account.document.AccountReferenceDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountReferenceRepository extends MongoRepository<AccountReferenceDocument, String> {
    Optional<AccountReferenceDocument> findByCode(String code);
}
