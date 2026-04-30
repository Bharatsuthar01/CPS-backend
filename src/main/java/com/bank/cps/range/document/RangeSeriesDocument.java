package com.bank.cps.range.document;

import com.bank.cps.common.document.BaseDocument;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("series_ranges")
public class RangeSeriesDocument extends BaseDocument {

    private String bankCode;
    private String branchCode;
    private String accountType;
    private Long startNumber;
    private Long endNumber;
    private Long currentNumber;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Long startNumber) {
        this.startNumber = startNumber;
    }

    public Long getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(Long endNumber) {
        this.endNumber = endNumber;
    }

    public Long getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Long currentNumber) {
        this.currentNumber = currentNumber;
    }
}