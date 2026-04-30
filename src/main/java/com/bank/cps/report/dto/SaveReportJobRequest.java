package com.bank.cps.report.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveReportJobRequest(@NotBlank String code, @NotBlank String name, String description) {}
