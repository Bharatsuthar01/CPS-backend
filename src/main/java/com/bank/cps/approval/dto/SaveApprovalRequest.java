package com.bank.cps.approval.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveApprovalRequest(@NotBlank String code, @NotBlank String name, String description) {}
