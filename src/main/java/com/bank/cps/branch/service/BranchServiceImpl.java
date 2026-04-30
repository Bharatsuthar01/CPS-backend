package com.bank.cps.branch.service;

import com.bank.cps.branch.document.BranchDocument;
import com.bank.cps.branch.dto.SaveBranchRequest;
import com.bank.cps.branch.repository.BranchRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository repository;

    public BranchServiceImpl(BranchRepository repository) {
        this.repository = repository;
    }

    public BranchDocument save(SaveBranchRequest request) {
        BranchDocument doc = repository.findByCode(request.code()).orElseGet(BranchDocument::new);
        if (doc.getId() == null)
            doc.setId(UUID.randomUUID().toString());
        doc.setCode(request.code());
        doc.setName(request.name());
        doc.setDescription(request.description());
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }

    public List<BranchDocument> findAll() {
        return repository.findAll();
    }
}
