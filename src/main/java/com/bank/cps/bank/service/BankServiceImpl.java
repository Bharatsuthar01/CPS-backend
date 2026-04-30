package com.bank.cps.bank.service;

import com.bank.cps.bank.document.BankDocument;
import com.bank.cps.bank.dto.SaveBankRequest;
import com.bank.cps.bank.repository.BankRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {
    private final BankRepository repository;

    public BankServiceImpl(BankRepository repository) {
        this.repository = repository;
    }

    public BankDocument save(SaveBankRequest request) {
        BankDocument doc = repository.findByCode(request.code()).orElseGet(BankDocument::new);
        if (doc.getId() == null)
            doc.setId(UUID.randomUUID().toString());
        doc.setCode(request.code());
        doc.setName(request.name());
        doc.setDescription(request.description());
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }

    public List<BankDocument> findAll() {
        return repository.findAll();
    }
}
