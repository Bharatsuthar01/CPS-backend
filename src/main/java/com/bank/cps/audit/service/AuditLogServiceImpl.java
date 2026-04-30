package com.bank.cps.audit.service;

import com.bank.cps.audit.document.AuditLogDocument;
import com.bank.cps.audit.dto.SaveAuditLogRequest;
import com.bank.cps.audit.repository.AuditLogRepository;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogRepository repository;
    public AuditLogServiceImpl(AuditLogRepository repository) { this.repository = repository; }
    public AuditLogDocument save(SaveAuditLogRequest request) {
        AuditLogDocument doc = new AuditLogDocument();
        doc.setId(UUID.randomUUID().toString());
        doc.setMethodName("MANUAL_AUDIT");
        doc.setArguments(Map.of(
            "code", request.code(),
            "name", request.name(),
            "description", request.description() != null ? request.description() : ""
        ));
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }
    public List<AuditLogDocument> findAll() { return repository.findAll(); }
}
