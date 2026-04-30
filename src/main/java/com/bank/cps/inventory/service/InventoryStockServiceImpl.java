package com.bank.cps.inventory.service;

import com.bank.cps.inventory.document.InventoryStockDocument;
import com.bank.cps.inventory.dto.SaveInventoryStockRequest;
import com.bank.cps.inventory.repository.InventoryStockRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class InventoryStockServiceImpl implements InventoryStockService {
    private final InventoryStockRepository repository;
    public InventoryStockServiceImpl(InventoryStockRepository repository) { this.repository = repository; }
    public InventoryStockDocument save(SaveInventoryStockRequest request) {
        InventoryStockDocument doc = repository.findByCode(request.code()).orElseGet(InventoryStockDocument::new);
        if (doc.getId() == null) doc.setId(UUID.randomUUID().toString());
        // Removed stub assignments (code, name, description)
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }
    public List<InventoryStockDocument> findAll() { return repository.findAll(); }
}
