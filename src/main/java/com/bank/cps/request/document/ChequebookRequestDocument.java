package com.bank.cps.request.document;

import com.bank.cps.common.document.BaseDocument;
import com.bank.cps.request.enums.RequestStatus;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("chequebook_requests")
public class ChequebookRequestDocument extends BaseDocument {
    private String requestRefNo;
    private String branchCode;
    private String accountNumber;
    private String requestSource;
    private String priority;
    private Integer bookSize;
    private RequestStatus requestStatus;
    private String makerRemarks;
    private List<RequestHistoryItem> history = new ArrayList<>();

    public String getRequestRefNo() { return requestRefNo; }
    public void setRequestRefNo(String requestRefNo) { this.requestRefNo = requestRefNo; }
    public String getBranchCode() { return branchCode; }
    public void setBranchCode(String branchCode) { this.branchCode = branchCode; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getRequestSource() { return requestSource; }
    public void setRequestSource(String requestSource) { this.requestSource = requestSource; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public Integer getBookSize() { return bookSize; }
    public void setBookSize(Integer bookSize) { this.bookSize = bookSize; }
    public RequestStatus getRequestStatus() { return requestStatus; }
    public void setRequestStatus(RequestStatus requestStatus) { this.requestStatus = requestStatus; }
    public String getMakerRemarks() { return makerRemarks; }
    public void setMakerRemarks(String makerRemarks) { this.makerRemarks = makerRemarks; }
    public List<RequestHistoryItem> getHistory() { return history; }
    public void setHistory(List<RequestHistoryItem> history) { this.history = history; }
}
