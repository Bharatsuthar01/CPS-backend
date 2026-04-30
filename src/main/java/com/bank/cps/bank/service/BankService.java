package com.bank.cps.bank.service;

import com.bank.cps.bank.document.BankDocument;
import com.bank.cps.bank.dto.SaveBankRequest;
import java.util.List;

public interface BankService {
    BankDocument save(SaveBankRequest request);
    List<BankDocument> findAll();
}
