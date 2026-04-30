package com.bank.cps.reprint.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveReprintRequestRequest(@NotBlank String code, @NotBlank String name, String description) {}
