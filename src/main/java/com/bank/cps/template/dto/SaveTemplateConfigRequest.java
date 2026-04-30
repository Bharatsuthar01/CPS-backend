package com.bank.cps.template.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveTemplateConfigRequest(@NotBlank String code, @NotBlank String name, String description) {}
