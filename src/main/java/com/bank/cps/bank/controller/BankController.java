package com.bank.cps.bank.controller;

import com.bank.cps.bank.document.BankDocument;
import com.bank.cps.bank.dto.SaveBankRequest;
import com.bank.cps.bank.service.BankService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    private final BankService service;
    public BankController(BankService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<BankDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<BankDocument> save(@Valid @RequestBody SaveBankRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
