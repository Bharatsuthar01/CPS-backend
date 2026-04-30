package com.bank.cps.account.service;

import com.bank.cps.account.document.AccountReferenceDocument;
import com.bank.cps.account.dto.SaveAccountReferenceRequest;
import com.bank.cps.account.repository.AccountReferenceRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AccountReferenceServiceImpl implements AccountReferenceService {
    private final AccountReferenceRepository repository;

    public AccountReferenceServiceImpl(AccountReferenceRepository repository) {
        this.repository = repository;
    }

    public AccountReferenceDocument save(SaveAccountReferenceRequest request) {
        AccountReferenceDocument doc = repository.findByCode(request.code()).orElseGet(AccountReferenceDocument::new);
        if (doc.getId() == null)
            doc.setId(UUID.randomUUID().toString());
        doc.setCode(request.code());
        doc.setName(request.name());
        doc.setDescription(request.description());
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }

    public List<AccountReferenceDocument> findAll() {
        return repository.findAll();
    }
}
