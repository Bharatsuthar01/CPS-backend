package com.bank.cps.print.controller;

import com.bank.cps.print.document.PrintJobDocument;
import com.bank.cps.print.dto.SavePrintJobRequest;
import com.bank.cps.print.service.PrintJobService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/print-jobs")
public class PrintJobController {
    private final PrintJobService service;
    public PrintJobController(PrintJobService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<PrintJobDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<PrintJobDocument> save(@Valid @RequestBody SavePrintJobRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
