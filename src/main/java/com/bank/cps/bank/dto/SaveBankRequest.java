package com.bank.cps.bank.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveBankRequest(@NotBlank String code, @NotBlank String name, String description) {}
