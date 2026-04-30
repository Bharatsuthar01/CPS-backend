package com.bank.cps.notification.controller;

import com.bank.cps.notification.document.NotificationMessageDocument;
import com.bank.cps.notification.dto.SaveNotificationMessageRequest;
import com.bank.cps.notification.service.NotificationMessageService;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationMessageController {
    private final NotificationMessageService service;
    public NotificationMessageController(NotificationMessageService service) { this.service = service; }

    @GetMapping
    public ApiResponse<List<NotificationMessageDocument>> list() {
        return ApiResponse.ok("Fetched", CorrelationIdFilter.current(), service.findAll());
    }

    @PostMapping
    public ApiResponse<NotificationMessageDocument> save(@Valid @RequestBody SaveNotificationMessageRequest request) {
        return ApiResponse.ok("Saved", CorrelationIdFilter.current(), service.save(request));
    }
}
