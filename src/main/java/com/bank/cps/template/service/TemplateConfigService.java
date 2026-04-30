package com.bank.cps.template.service;

import com.bank.cps.template.document.TemplateConfigDocument;
import com.bank.cps.template.dto.SaveTemplateConfigRequest;
import java.util.List;

public interface TemplateConfigService {
    TemplateConfigDocument save(SaveTemplateConfigRequest request);
    List<TemplateConfigDocument> findAll();
}
