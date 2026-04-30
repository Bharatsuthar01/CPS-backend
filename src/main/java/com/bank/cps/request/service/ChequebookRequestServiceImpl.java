package com.bank.cps.request.service;

import com.bank.cps.common.exception.BusinessException;
import com.bank.cps.common.security.CurrentUser;
import com.bank.cps.common.util.ReferenceGenerator;
import com.bank.cps.request.document.ChequebookRequestDocument;
import com.bank.cps.request.document.RequestHistoryItem;
import com.bank.cps.request.dto.CreateChequebookRequest;
import com.bank.cps.request.enums.RequestStatus;
import com.bank.cps.request.repository.ChequebookRequestRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings("null")
public class ChequebookRequestServiceImpl implements ChequebookRequestService {
    private final ChequebookRequestRepository repository;
    private final ReferenceGenerator referenceGenerator;

    public ChequebookRequestServiceImpl(ChequebookRequestRepository repository, ReferenceGenerator referenceGenerator) {
        this.repository = repository;
        this.referenceGenerator = referenceGenerator;
    }

    @Override
    public ChequebookRequestDocument create(CreateChequebookRequest request) {
        if (repository.existsByAccountNumberAndRequestStatusIn(request.accountNumber(), List.of(RequestStatus.DRAFT, RequestStatus.PENDING_APPROVAL, RequestStatus.APPROVED))) {
            throw new BusinessException("REQUEST_DUPLICATE", "Active request already exists for account");
        }
        ChequebookRequestDocument doc = new ChequebookRequestDocument();
        doc.setId(UUID.randomUUID().toString());
        doc.setRequestRefNo(referenceGenerator.next("CBR"));
        doc.setBranchCode(request.branchCode());
        doc.setAccountNumber(request.accountNumber());
        doc.setRequestSource(request.requestSource());
        doc.setPriority(request.priority());
        doc.setBookSize(request.bookSize());
        doc.setMakerRemarks(request.makerRemarks());
        doc.setRequestStatus(RequestStatus.DRAFT);
        doc.setStatus("ACTIVE");
        addHistory(doc, "CREATED", request.makerRemarks());
        return repository.save(doc);
    }

    @Override
    public ChequebookRequestDocument submit(String id) {
        ChequebookRequestDocument doc = repository.findById(id).orElseThrow(() -> new BusinessException("REQUEST_NOT_FOUND", "Request not found"));
        if (doc.getRequestStatus() != RequestStatus.DRAFT) {
            throw new BusinessException("REQUEST_INVALID_STATE", "Only draft can be submitted");
        }
        doc.setRequestStatus(RequestStatus.PENDING_APPROVAL);
        addHistory(doc, "SUBMITTED", "");
        return repository.save(doc);
    }

    @Override
    public ChequebookRequestDocument approve(String id) {
        ChequebookRequestDocument doc = repository.findById(id).orElseThrow(() -> new BusinessException("REQUEST_NOT_FOUND", "Request not found"));
        if (doc.getRequestStatus() != RequestStatus.PENDING_APPROVAL) {
            throw new BusinessException("REQUEST_INVALID_STATE", "Only pending requests can be approved");
        }
        doc.setRequestStatus(RequestStatus.APPROVED);
        addHistory(doc, "APPROVED", "");
        return repository.save(doc);
    }

    @Override
    public ChequebookRequestDocument reject(String id, String remarks) {
        ChequebookRequestDocument doc = repository.findById(id).orElseThrow(() -> new BusinessException("REQUEST_NOT_FOUND", "Request not found"));
        if (doc.getRequestStatus() != RequestStatus.PENDING_APPROVAL) {
            throw new BusinessException("REQUEST_INVALID_STATE", "Only pending requests can be rejected");
        }
        doc.setRequestStatus(RequestStatus.REJECTED);
        addHistory(doc, "REJECTED", remarks);
        return repository.save(doc);
    }

    public ChequebookRequestDocument cancel(String id, String remarks) {
        ChequebookRequestDocument doc = repository.findById(id).orElseThrow(() -> new BusinessException("REQUEST_NOT_FOUND", "Request not found"));
        if (doc.getRequestStatus() != RequestStatus.DRAFT && doc.getRequestStatus() != RequestStatus.PENDING_APPROVAL) { // GUARD
            throw new BusinessException("REQUEST_INVALID_STATE", "Only draft or pending requests can be cancelled");
        }
        doc.setRequestStatus(RequestStatus.CANCELLED);
        addHistory(doc, "CANCELLED", remarks);
        return repository.save(doc);
    }

    public ChequebookRequestDocument requestReprint(String id, String remarks) {
        ChequebookRequestDocument doc = repository.findById(id).orElseThrow(() -> new BusinessException("REQUEST_NOT_FOUND", "Request not found"));
        if (doc.getRequestStatus() != RequestStatus.PRINTED && doc.getRequestStatus() != RequestStatus.SPOILED) { // GUARD
            throw new BusinessException("REQUEST_INVALID_STATE", "Only printed or spoiled requests can be reprinted");
        }
        doc.setRequestStatus(RequestStatus.REPRINT_REQUESTED);
        addHistory(doc, "REPRINT_REQUESTED", remarks);
        return repository.save(doc);
    }

    @Override
    public List<ChequebookRequestDocument> pending() {
        return repository.findByRequestStatus(RequestStatus.PENDING_APPROVAL);
    }

    private void addHistory(ChequebookRequestDocument doc, String action, @org.springframework.lang.Nullable String remarks) {
        RequestHistoryItem item = new RequestHistoryItem();
        item.setAction(action);
        
        String username = null;
        try { username = CurrentUser.username(); } catch (Exception ignored) {}
        
        item.setActionBy(username != null ? username : "system");
        item.setActionAt(Instant.now());
        item.setRemarks(remarks);
        doc.getHistory().add(item);
    }
}
