package com.bank.cps.template.repository;

import com.bank.cps.template.document.TemplateConfigDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateConfigRepository extends MongoRepository<TemplateConfigDocument, String> {
    Optional<TemplateConfigDocument> findByCode(String code);
}
