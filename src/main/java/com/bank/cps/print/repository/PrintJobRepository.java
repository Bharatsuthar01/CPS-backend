package com.bank.cps.print.repository;

import com.bank.cps.print.document.PrintJobDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrintJobRepository extends MongoRepository<PrintJobDocument, String> {
    Optional<PrintJobDocument> findByRequestRefNo(String requestRefNo);
}
