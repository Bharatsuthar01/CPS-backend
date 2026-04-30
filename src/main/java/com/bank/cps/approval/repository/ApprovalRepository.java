package com.bank.cps.approval.repository;

import com.bank.cps.approval.document.ApprovalDocument;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApprovalRepository extends MongoRepository<ApprovalDocument, String> {
    Optional<ApprovalDocument> findByRequestRefNo(String requestRefNo);

    List<ApprovalDocument> findAllByRequestRefNo(String requestRefNo);
}