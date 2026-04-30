package com.bank.cps.branch.repository;

import com.bank.cps.branch.document.BranchDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepository extends MongoRepository<BranchDocument, String> {
    Optional<BranchDocument> findByCode(String code);
}
