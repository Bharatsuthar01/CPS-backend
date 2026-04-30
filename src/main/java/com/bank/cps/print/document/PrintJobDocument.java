package com.bank.cps.print.document;

import com.bank.cps.common.document.BaseDocument;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("print_jobs")
public class PrintJobDocument extends BaseDocument {
    private String jobId;
    private String requestRefNo;
    private String printerId;
    private String fileName;
    private Integer totalCheques;
    // ← status removed; inherited from BaseDocument (IN_PROGRESS, COMPLETED,
    // FAILED)
    private String printedBy;
    private Instant startedAt;
    private Instant completedAt;
    private String errorDetails;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getRequestRefNo() {
        return requestRefNo;
    }

    public void setRequestRefNo(String requestRefNo) {
        this.requestRefNo = requestRefNo;
    }

    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getTotalCheques() {
        return totalCheques;
    }

    public void setTotalCheques(Integer totalCheques) {
        this.totalCheques = totalCheques;
    }

    // ← getStatus()/setStatus() removed; inherited from BaseDocument

    public String getPrintedBy() {
        return printedBy;
    }

    public void setPrintedBy(String printedBy) {
        this.printedBy = printedBy;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}