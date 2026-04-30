package com.bank.cps.integration.controller;

import com.bank.cps.integration.document.IntegrationJobDocument;
import com.bank.cps.integration.dto.SaveIntegrationJobRequest;
import com.bank.cps.integration.service.IntegrationJobService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/integrations")
public class IntegrationJobController {
    private final IntegrationJobService service;
    public IntegrationJobController(IntegrationJobService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<IntegrationJobDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<IntegrationJobDocument> save(@Valid @RequestBody SaveIntegrationJobRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
