package com.bank.cps.inventory.controller;

import com.bank.cps.inventory.document.InventoryStockDocument;
import com.bank.cps.inventory.dto.SaveInventoryStockRequest;
import com.bank.cps.inventory.service.InventoryStockService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryStockController {
    private final InventoryStockService service;
    public InventoryStockController(InventoryStockService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<InventoryStockDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<InventoryStockDocument> save(@Valid @RequestBody SaveInventoryStockRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
