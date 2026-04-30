package com.bank.cps.inventory.service;

import com.bank.cps.inventory.document.InventoryStockDocument;
import com.bank.cps.inventory.dto.SaveInventoryStockRequest;
import java.util.List;

public interface InventoryStockService {
    InventoryStockDocument save(SaveInventoryStockRequest request);
    List<InventoryStockDocument> findAll();
}
