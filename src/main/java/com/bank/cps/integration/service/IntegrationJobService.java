package com.bank.cps.integration.service;

import com.bank.cps.integration.document.IntegrationJobDocument;
import com.bank.cps.integration.dto.SaveIntegrationJobRequest;
import java.util.List;

public interface IntegrationJobService {
    IntegrationJobDocument save(SaveIntegrationJobRequest request);
    List<IntegrationJobDocument> findAll();
}
