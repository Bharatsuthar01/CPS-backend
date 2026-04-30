package com.bank.cps.account.service;

import com.bank.cps.account.document.AccountReferenceDocument;
import com.bank.cps.account.dto.SaveAccountReferenceRequest;
import java.util.List;

public interface AccountReferenceService {
    AccountReferenceDocument save(SaveAccountReferenceRequest request);
    List<AccountReferenceDocument> findAll();
}
