package com.bank.cps.branch.service;

import com.bank.cps.branch.document.BranchDocument;
import com.bank.cps.branch.dto.SaveBranchRequest;
import java.util.List;

public interface BranchService {
    BranchDocument save(SaveBranchRequest request);
    List<BranchDocument> findAll();
}
