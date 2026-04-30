package com.bank.cps.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

@Component
public class ReferenceGenerator {
    private final AtomicInteger counter = new AtomicInteger(1);
    public String next(String prefix) {
        return prefix + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + String.format("%05d", counter.getAndIncrement());
    }
}
