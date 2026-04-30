package com.bank.cps.notification.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveNotificationMessageRequest(@NotBlank String code, @NotBlank String name, String description) {}
