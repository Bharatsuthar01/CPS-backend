package com.bank.cps.report.repository;

import com.bank.cps.report.document.ReportJobDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportJobRepository extends MongoRepository<ReportJobDocument, String> {
    Optional<ReportJobDocument> findByCode(String code);
}
