package com.bank.cps.approval.controller;

import com.bank.cps.approval.document.ApprovalDocument;
import com.bank.cps.approval.dto.SaveApprovalRequest;
import com.bank.cps.approval.service.ApprovalService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/approvals")
public class ApprovalController {
    private final ApprovalService service;
    public ApprovalController(ApprovalService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<ApprovalDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<ApprovalDocument> save(@Valid @RequestBody SaveApprovalRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
