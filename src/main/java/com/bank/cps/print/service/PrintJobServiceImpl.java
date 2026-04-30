package com.bank.cps.print.service;

import com.bank.cps.print.document.PrintJobDocument;
import com.bank.cps.print.dto.SavePrintJobRequest;
import com.bank.cps.print.repository.PrintJobRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PrintJobServiceImpl implements PrintJobService {
    private final PrintJobRepository repository;

    public PrintJobServiceImpl(PrintJobRepository repository) {
        this.repository = repository;
    }

    public PrintJobDocument save(SavePrintJobRequest request) {
        PrintJobDocument doc = repository.findByRequestRefNo(request.requestRefNo()).orElseGet(PrintJobDocument::new);
        if (doc.getId() == null) {
            doc.setId(UUID.randomUUID().toString());
            doc.setJobId(UUID.randomUUID().toString());
            doc.setStartedAt(Instant.now());
        }
        doc.setRequestRefNo(request.requestRefNo());
        doc.setPrinterId(request.printerId());
        doc.setFileName(request.fileName());
        doc.setTotalCheques(request.totalCheques());
        doc.setStatus("IN_PROGRESS");
        return repository.save(doc);
    }

    public List<PrintJobDocument> findAll() {
        return repository.findAll();
    }
}
