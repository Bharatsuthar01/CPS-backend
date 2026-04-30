package com.bank.cps.reprint.service;

import com.bank.cps.reprint.document.ReprintRequestDocument;
import com.bank.cps.reprint.dto.SaveReprintRequestRequest;
import com.bank.cps.reprint.repository.ReprintRequestRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ReprintRequestServiceImpl implements ReprintRequestService {
    private final ReprintRequestRepository repository;

    public ReprintRequestServiceImpl(ReprintRequestRepository repository) {
        this.repository = repository;
    }

    public ReprintRequestDocument save(SaveReprintRequestRequest request) {
        ReprintRequestDocument doc = repository.findByCode(request.code()).orElseGet(ReprintRequestDocument::new);
        if (doc.getId() == null)
            doc.setId(UUID.randomUUID().toString());
        doc.setCode(request.code());
        doc.setName(request.name());
        doc.setDescription(request.description());
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }

    public List<ReprintRequestDocument> findAll() {
        return repository.findAll();
    }
}
