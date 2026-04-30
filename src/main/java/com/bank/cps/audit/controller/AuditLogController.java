package com.bank.cps.audit.controller;

import com.bank.cps.audit.document.AuditLogDocument;
import com.bank.cps.audit.dto.SaveAuditLogRequest;
import com.bank.cps.audit.service.AuditLogService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {
    private final AuditLogService service;
    public AuditLogController(AuditLogService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<AuditLogDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<AuditLogDocument> save(@Valid @RequestBody SaveAuditLogRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
