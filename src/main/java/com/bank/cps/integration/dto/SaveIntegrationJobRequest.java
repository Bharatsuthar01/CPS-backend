package com.bank.cps.integration.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveIntegrationJobRequest(@NotBlank String code, @NotBlank String name, String description) {}
