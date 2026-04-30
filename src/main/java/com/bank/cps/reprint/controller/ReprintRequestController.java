package com.bank.cps.reprint.controller;

import com.bank.cps.reprint.document.ReprintRequestDocument;
import com.bank.cps.reprint.dto.SaveReprintRequestRequest;
import com.bank.cps.reprint.service.ReprintRequestService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reprints")
public class ReprintRequestController {
    private final ReprintRequestService service;
    public ReprintRequestController(ReprintRequestService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<ReprintRequestDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<ReprintRequestDocument> save(@Valid @RequestBody SaveReprintRequestRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
