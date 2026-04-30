package com.bank.cps.template.controller;

import com.bank.cps.template.document.TemplateConfigDocument;
import com.bank.cps.template.dto.SaveTemplateConfigRequest;
import com.bank.cps.template.service.TemplateConfigService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/templates")
public class TemplateConfigController {
    private final TemplateConfigService service;
    public TemplateConfigController(TemplateConfigService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<TemplateConfigDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<TemplateConfigDocument> save(@Valid @RequestBody SaveTemplateConfigRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
