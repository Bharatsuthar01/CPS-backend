package com.bank.cps.report.controller;

import com.bank.cps.report.document.ReportJobDocument;
import com.bank.cps.report.dto.SaveReportJobRequest;
import com.bank.cps.report.service.ReportJobService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportJobController {
    private final ReportJobService service;
    public ReportJobController(ReportJobService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<ReportJobDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<ReportJobDocument> save(@Valid @RequestBody SaveReportJobRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
