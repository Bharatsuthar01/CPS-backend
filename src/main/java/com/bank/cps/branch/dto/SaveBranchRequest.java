package com.bank.cps.branch.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveBranchRequest(@NotBlank String code, @NotBlank String name, String description) {}
