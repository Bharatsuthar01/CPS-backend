package com.bank.cps.audit.service;

import com.bank.cps.audit.document.AuditLogDocument;
import com.bank.cps.audit.dto.SaveAuditLogRequest;
import java.util.List;

public interface AuditLogService {
    AuditLogDocument save(SaveAuditLogRequest request);
    List<AuditLogDocument> findAll();
}
