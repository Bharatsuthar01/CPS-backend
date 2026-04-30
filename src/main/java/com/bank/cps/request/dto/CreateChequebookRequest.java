package com.bank.cps.request.dto;

import com.bank.cps.common.validation.EnumValue;
import com.bank.cps.request.enums.Priority;
import com.bank.cps.request.enums.RequestSource;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateChequebookRequest(
        @NotBlank String branchCode, 
        @NotBlank String accountNumber,
        @NotBlank @EnumValue(enumClass = RequestSource.class, message = "Invalid request source") String requestSource, 
        @NotBlank @EnumValue(enumClass = Priority.class, message = "Invalid priority") String priority,
        @NotNull @Min(1) Integer bookSize, 
        String makerRemarks) {}
