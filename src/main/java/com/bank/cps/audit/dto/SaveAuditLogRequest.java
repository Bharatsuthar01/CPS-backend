package com.bank.cps.audit.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveAuditLogRequest(@NotBlank String code, @NotBlank String name, String description) {}
