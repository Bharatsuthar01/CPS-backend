package com.bank.cps.integration.service;

import com.bank.cps.integration.document.IntegrationJobDocument;
import com.bank.cps.integration.dto.SaveIntegrationJobRequest;
import com.bank.cps.integration.repository.IntegrationJobRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class IntegrationJobServiceImpl implements IntegrationJobService {
    private final IntegrationJobRepository repository;

    public IntegrationJobServiceImpl(IntegrationJobRepository repository) {
        this.repository = repository;
    }

    public IntegrationJobDocument save(SaveIntegrationJobRequest request) {
        IntegrationJobDocument doc = repository.findByCode(request.code()).orElseGet(IntegrationJobDocument::new);
        if (doc.getId() == null)
            doc.setId(UUID.randomUUID().toString());
        doc.setCode(request.code());
        doc.setName(request.name());
        doc.setDescription(request.description());
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }

    public List<IntegrationJobDocument> findAll() {
        return repository.findAll();
    }
}
