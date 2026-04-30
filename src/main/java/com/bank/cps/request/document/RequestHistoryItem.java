package com.bank.cps.request.document;

import java.time.Instant;

public class RequestHistoryItem {
    private String action;
    private String actionBy;
    private Instant actionAt;
    private String remarks;
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    public String getActionBy() { return actionBy; }
    public void setActionBy(String actionBy) { this.actionBy = actionBy; }
    public Instant getActionAt() { return actionAt; }
    public void setActionAt(Instant actionAt) { this.actionAt = actionAt; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
