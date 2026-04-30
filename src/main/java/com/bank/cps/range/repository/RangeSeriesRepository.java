package com.bank.cps.range.repository;

import com.bank.cps.range.document.RangeSeriesDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RangeSeriesRepository extends MongoRepository<RangeSeriesDocument, String> {
    Optional<RangeSeriesDocument> findByCode(String code);
}
