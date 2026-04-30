package com.bank.cps.report.service;

import com.bank.cps.report.document.ReportJobDocument;
import com.bank.cps.report.dto.SaveReportJobRequest;
import java.util.List;

public interface ReportJobService {
    ReportJobDocument save(SaveReportJobRequest request);
    List<ReportJobDocument> findAll();
}
