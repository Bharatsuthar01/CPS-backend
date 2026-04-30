package com.bank.cps.request.service;

import com.bank.cps.common.exception.BusinessException;
import com.bank.cps.common.util.ReferenceGenerator;
import com.bank.cps.request.document.ChequebookRequestDocument;
import com.bank.cps.request.dto.CreateChequebookRequest;
import com.bank.cps.request.enums.RequestStatus;
import com.bank.cps.request.repository.ChequebookRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
class ChequebookRequestServiceImplTest {

    @Mock
    private ChequebookRequestRepository repository;

    @Mock
    private ReferenceGenerator referenceGenerator;

    @InjectMocks
    private ChequebookRequestServiceImpl service;

    @Test
    void testCreateDuplicateRequestThrowsException() {
        CreateChequebookRequest request = new CreateChequebookRequest("001", "12345", "ONLINE", "HIGH", 50, "remarks");
        when(repository.existsByAccountNumberAndRequestStatusIn(eq("12345"), anyList())).thenReturn(true);
        
        BusinessException exception = assertThrows(BusinessException.class, () -> service.create(request));
        assertEquals("REQUEST_DUPLICATE", exception.getCode());
    }

    @Test
    void testReferenceNumberGeneratorUsedInCreate() {
        CreateChequebookRequest request = new CreateChequebookRequest("001", "12345", "ONLINE", "HIGH", 50, "remarks");
        when(repository.existsByAccountNumberAndRequestStatusIn(eq("12345"), anyList())).thenReturn(false);
        when(referenceGenerator.next("CBR")).thenReturn("CBR-999");
        when(repository.save(any(ChequebookRequestDocument.class))).thenAnswer(i -> i.getArgument(0));

        ChequebookRequestDocument doc = service.create(request);
        
        assertEquals("CBR-999", doc.getRequestRefNo());
        verify(referenceGenerator).next("CBR");
    }

    @Test
    void testSubmitValidState() {
        ChequebookRequestDocument doc = new ChequebookRequestDocument();
        doc.setRequestStatus(RequestStatus.DRAFT);
        when(repository.findById("id1")).thenReturn(Optional.of(doc));
        when(repository.save(any(ChequebookRequestDocument.class))).thenReturn(doc);
        
        service.submit("id1");
        
        assertEquals(RequestStatus.PENDING_APPROVAL, doc.getRequestStatus());
    }

    @Test
    void testSubmitInvalidState() {
        ChequebookRequestDocument doc = new ChequebookRequestDocument();
        doc.setRequestStatus(RequestStatus.APPROVED);
        when(repository.findById("id1")).thenReturn(Optional.of(doc));
        
        assertThrows(BusinessException.class, () -> service.submit("id1"));
    }

    @Test
    void testApproveValidState() {
        ChequebookRequestDocument doc = new ChequebookRequestDocument();
        doc.setRequestStatus(RequestStatus.PENDING_APPROVAL);
        when(repository.findById("id1")).thenReturn(Optional.of(doc));
        when(repository.save(any(ChequebookRequestDocument.class))).thenReturn(doc);
        
        service.approve("id1");
        
        assertEquals(RequestStatus.APPROVED, doc.getRequestStatus());
    }

    @Test
    void testApproveInvalidStateThrowsException() {
        ChequebookRequestDocument doc = new ChequebookRequestDocument();
        doc.setRequestStatus(RequestStatus.DRAFT);
        when(repository.findById("id1")).thenReturn(Optional.of(doc));
        
        assertThrows(BusinessException.class, () -> service.approve("id1"));
    }
    
    @Test
    void testRejectValidState() {
        ChequebookRequestDocument doc = new ChequebookRequestDocument();
        doc.setRequestStatus(RequestStatus.PENDING_APPROVAL);
        when(repository.findById("id1")).thenReturn(Optional.of(doc));
        when(repository.save(any(ChequebookRequestDocument.class))).thenReturn(doc);
        
        service.reject("id1", "Insufficient details");
        
        assertEquals(RequestStatus.REJECTED, doc.getRequestStatus());
    }
    
    @Test
    void testRejectInvalidStateThrowsException() {
        ChequebookRequestDocument doc = new ChequebookRequestDocument();
        doc.setRequestStatus(RequestStatus.PRINTED);
        when(repository.findById("id1")).thenReturn(Optional.of(doc));
        
        assertThrows(BusinessException.class, () -> service.reject("id1", "Rejecting from printed"));
    }
}
