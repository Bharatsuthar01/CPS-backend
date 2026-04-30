package com.bank.cps.request.controller;

import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import com.bank.cps.request.document.ChequebookRequestDocument;
import com.bank.cps.request.dto.CreateChequebookRequest;
import com.bank.cps.request.service.ChequebookRequestService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class ChequebookRequestController {
    private final ChequebookRequestService service;
    public ChequebookRequestController(ChequebookRequestService service) { this.service = service; }

    @PostMapping
    public ApiResponse<ChequebookRequestDocument> create(@Valid @RequestBody CreateChequebookRequest request) {
        return ApiResponse.ok("Request created", CorrelationIdFilter.current(), service.create(request));
    }

    @PostMapping("/{id}/submit")
    public ApiResponse<ChequebookRequestDocument> submit(@PathVariable String id) {
        return ApiResponse.ok("Request submitted", CorrelationIdFilter.current(), service.submit(id));
    }

    @PostMapping("/{id}/approve")
    public ApiResponse<ChequebookRequestDocument> approve(@PathVariable String id) {
        return ApiResponse.ok("Request approved", CorrelationIdFilter.current(), service.approve(id));
    }

    @PostMapping("/{id}/reject")
    public ApiResponse<ChequebookRequestDocument> reject(@PathVariable String id, @RequestParam(required = false) String remarks) {
        return ApiResponse.ok("Request rejected", CorrelationIdFilter.current(), service.reject(id, remarks));
    }

    @GetMapping("/pending")
    public ApiResponse<List<ChequebookRequestDocument>> pending() {
        return ApiResponse.ok("Pending requests fetched", CorrelationIdFilter.current(), service.pending());
    }
}
