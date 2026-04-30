package com.bank.cps.reprint.service;

import com.bank.cps.reprint.document.ReprintRequestDocument;
import com.bank.cps.reprint.dto.SaveReprintRequestRequest;
import java.util.List;

public interface ReprintRequestService {
    ReprintRequestDocument save(SaveReprintRequestRequest request);
    List<ReprintRequestDocument> findAll();
}
