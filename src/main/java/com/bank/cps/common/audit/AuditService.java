package com.bank.cps.common.audit;

public interface AuditService {
    void record(AuditEvent event);
}
