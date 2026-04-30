package com.bank.cps.approval.service;

import com.bank.cps.approval.document.ApprovalDocument;
import com.bank.cps.approval.dto.SaveApprovalRequest;
import java.util.List;

public interface ApprovalService {
    ApprovalDocument save(SaveApprovalRequest request);
    List<ApprovalDocument> findAll();
}
