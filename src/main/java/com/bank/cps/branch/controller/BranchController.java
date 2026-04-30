package com.bank.cps.branch.controller;

import com.bank.cps.branch.document.BranchDocument;
import com.bank.cps.branch.dto.SaveBranchRequest;
import com.bank.cps.branch.service.BranchService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchService service;
    public BranchController(BranchService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<BranchDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<BranchDocument> save(@Valid @RequestBody SaveBranchRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
