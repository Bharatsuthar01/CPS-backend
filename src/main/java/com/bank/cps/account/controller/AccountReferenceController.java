package com.bank.cps.account.controller;

import com.bank.cps.account.document.AccountReferenceDocument;
import com.bank.cps.account.dto.SaveAccountReferenceRequest;
import com.bank.cps.account.service.AccountReferenceService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountReferenceController {
    private final AccountReferenceService service;
    public AccountReferenceController(AccountReferenceService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<AccountReferenceDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<AccountReferenceDocument> save(@Valid @RequestBody SaveAccountReferenceRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
