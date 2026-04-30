package com.bank.cps.range.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveRangeSeriesRequest(@NotBlank String code, @NotBlank String name, String description) {}
