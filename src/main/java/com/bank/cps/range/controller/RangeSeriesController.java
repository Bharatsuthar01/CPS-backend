package com.bank.cps.range.controller;

import com.bank.cps.range.document.RangeSeriesDocument;
import com.bank.cps.range.dto.SaveRangeSeriesRequest;
import com.bank.cps.range.service.RangeSeriesService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ranges")
public class RangeSeriesController {
    private final RangeSeriesService service;
    public RangeSeriesController(RangeSeriesService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<RangeSeriesDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<RangeSeriesDocument> save(@Valid @RequestBody SaveRangeSeriesRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
