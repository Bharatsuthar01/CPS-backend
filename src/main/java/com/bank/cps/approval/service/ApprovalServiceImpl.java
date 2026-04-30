package com.bank.cps.approval.service;

import com.bank.cps.approval.document.ApprovalDocument;
import com.bank.cps.approval.dto.SaveApprovalRequest;
import com.bank.cps.approval.repository.ApprovalRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl implements ApprovalService {
    private final ApprovalRepository repository;

    public ApprovalServiceImpl(ApprovalRepository repository) {
        this.repository = repository;
    }

    public ApprovalDocument save(SaveApprovalRequest request) {
        ApprovalDocument doc = repository.findByRequestRefNo(request.code()).orElseGet(ApprovalDocument::new);
        if (doc.getId() == null)
            doc.setId(UUID.randomUUID().toString());
        // Removed stub assignments (code, name, description)
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }

    public List<ApprovalDocument> findAll() {
        return repository.findAll();
    }
}
