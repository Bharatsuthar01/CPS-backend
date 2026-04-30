package com.bank.cps.template.service;

import com.bank.cps.template.document.TemplateConfigDocument;
import com.bank.cps.template.dto.SaveTemplateConfigRequest;
import com.bank.cps.template.repository.TemplateConfigRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TemplateConfigServiceImpl implements TemplateConfigService {
    private final TemplateConfigRepository repository;

    public TemplateConfigServiceImpl(TemplateConfigRepository repository) {
        this.repository = repository;
    }

    public TemplateConfigDocument save(SaveTemplateConfigRequest request) {
        TemplateConfigDocument doc = repository.findByCode(request.code()).orElseGet(TemplateConfigDocument::new);
        if (doc.getId() == null)
            doc.setId(UUID.randomUUID().toString());
        doc.setCode(request.code());
        doc.setName(request.name());
        doc.setDescription(request.description());
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }

    public List<TemplateConfigDocument> findAll() {
        return repository.findAll();
    }
}
