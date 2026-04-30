package com.bank.cps.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RateLimitFilter extends OncePerRequestFilter {
    private final int capacity;
    private final int refillPerMinute;
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public RateLimitFilter(@Value("${app.security.rate-limit.capacity:100}") int capacity,
            @Value("${app.security.rate-limit.refill-per-minute:100}") int refillPerMinute) {
        this.capacity = capacity;
        this.refillPerMinute = refillPerMinute;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().startsWith("/api/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String key = request.getRemoteAddr();
        Bucket bucket = buckets.computeIfAbsent(key, k -> new Bucket(capacity, Instant.now().getEpochSecond()));
        synchronized (bucket) {
            long now = Instant.now().getEpochSecond();
            long minutes = Math.max(0, (now - bucket.lastRefill) / 60);
            if (minutes > 0) {
                bucket.tokens = Math.min(capacity, bucket.tokens + (int) minutes * refillPerMinute);
                bucket.lastRefill = now;
            }
            if (bucket.tokens <= 0) {
                response.setStatus(429);
                response.getWriter().write("Too many requests");
                return;
            }
            bucket.tokens--;
        }
        filterChain.doFilter(request, response);
    }

    private static final class Bucket {
        int tokens;
        long lastRefill;

        Bucket(int tokens, long lastRefill) {
            this.tokens = tokens;
            this.lastRefill = lastRefill;
        }
    }
}
