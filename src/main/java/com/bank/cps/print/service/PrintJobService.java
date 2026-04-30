package com.bank.cps.print.service;

import com.bank.cps.print.document.PrintJobDocument;
import com.bank.cps.print.dto.SavePrintJobRequest;
import java.util.List;

public interface PrintJobService {
    PrintJobDocument save(SavePrintJobRequest request);
    List<PrintJobDocument> findAll();
}
