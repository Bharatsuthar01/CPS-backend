package com.bank.cps.inventory.repository;

import com.bank.cps.inventory.document.InventoryStockDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryStockRepository extends MongoRepository<InventoryStockDocument, String> {
    Optional<InventoryStockDocument> findByCode(String code);
}
