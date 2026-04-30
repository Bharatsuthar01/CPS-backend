package com.bank.cps.request.service;

import com.bank.cps.request.document.ChequebookRequestDocument;
import com.bank.cps.request.dto.CreateChequebookRequest;
import java.util.List;

public interface ChequebookRequestService {
    ChequebookRequestDocument create(CreateChequebookRequest request);
    ChequebookRequestDocument submit(String id);
    ChequebookRequestDocument approve(String id);
    ChequebookRequestDocument reject(String id, String remarks);
    ChequebookRequestDocument cancel(String id, String remarks);
    ChequebookRequestDocument requestReprint(String id, String remarks);
    List<ChequebookRequestDocument> pending();
}
