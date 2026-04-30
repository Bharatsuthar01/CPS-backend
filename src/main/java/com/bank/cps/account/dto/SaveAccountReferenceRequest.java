package com.bank.cps.account.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveAccountReferenceRequest(@NotBlank String code, @NotBlank String name, String description) {}
