package com.bank.cps.approval.document;

import com.bank.cps.common.document.BaseDocument;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document("approvals")
public class ApprovalDocument extends BaseDocument {

    private String requestRefNo;
    private String stepName;

    @Field("approval_status")
    private String approvalStatus; // PENDING, APPROVED, REJECTED

    private String approverId;
    private Instant approvalDate;
    private String comments;

    public String getRequestRefNo() {
        return requestRefNo;
    }

    public void setRequestRefNo(String requestRefNo) {
        this.requestRefNo = requestRefNo;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public Instant getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Instant approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}