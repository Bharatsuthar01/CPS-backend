package com.bank.cps.common.logging;

public final class LogSanitizer {
    private LogSanitizer() {}
    public static String mask(String value) {
        if (value == null || value.length() < 4) return "****";
        return "****" + value.substring(value.length() - 4);
    }
}
