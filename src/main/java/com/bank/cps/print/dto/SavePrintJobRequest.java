package com.bank.cps.print.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SavePrintJobRequest(
        @NotBlank String requestRefNo,
        @NotBlank String printerId,
        @NotBlank String fileName,
        @NotNull @Positive Integer totalCheques
) {}
