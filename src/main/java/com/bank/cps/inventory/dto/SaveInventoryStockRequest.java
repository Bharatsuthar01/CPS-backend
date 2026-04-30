package com.bank.cps.inventory.dto;

import jakarta.validation.constraints.NotBlank;

public record SaveInventoryStockRequest(@NotBlank String code, @NotBlank String name, String description) {}
