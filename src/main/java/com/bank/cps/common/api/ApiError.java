package com.bank.cps.common.api;

public record ApiError(String code, String field, String detail) {}
