package com.bank.cps.common.logging;

import com.bank.cps.common.constants.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    private static final String KEY = "cid";

    public static String current() {
        return Optional.ofNullable(MDC.get(KEY)).orElse("N/A");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String correlationId = Optional.ofNullable(request.getHeader(SecurityConstants.CORRELATION_ID)).orElse(UUID.randomUUID().toString());
        MDC.put(KEY, correlationId);
        response.setHeader(SecurityConstants.CORRELATION_ID, correlationId);
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(KEY);
        }
    }
}
