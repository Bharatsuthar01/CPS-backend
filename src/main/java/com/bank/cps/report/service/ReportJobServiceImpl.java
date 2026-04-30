package com.bank.cps.report.service;

import com.bank.cps.report.document.ReportJobDocument;
import com.bank.cps.report.dto.SaveReportJobRequest;
import com.bank.cps.report.repository.ReportJobRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ReportJobServiceImpl implements ReportJobService {
    private final ReportJobRepository repository;

    public ReportJobServiceImpl(ReportJobRepository repository) {
        this.repository = repository;
    }

    public ReportJobDocument save(SaveReportJobRequest request) {
        ReportJobDocument doc = repository.findByCode(request.code()).orElseGet(ReportJobDocument::new);
        if (doc.getId() == null)
            doc.setId(UUID.randomUUID().toString());
        doc.setCode(request.code());
        doc.setName(request.name());
        doc.setDescription(request.description());
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }

    public List<ReportJobDocument> findAll() {
        return repository.findAll();
    }
}
