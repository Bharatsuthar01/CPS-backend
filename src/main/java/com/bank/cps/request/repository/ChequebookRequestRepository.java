package com.bank.cps.request.repository;

import com.bank.cps.request.document.ChequebookRequestDocument;
import com.bank.cps.request.enums.RequestStatus;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChequebookRequestRepository extends MongoRepository<ChequebookRequestDocument, String> {
    List<ChequebookRequestDocument> findByRequestStatus(RequestStatus requestStatus);
    boolean existsByAccountNumberAndRequestStatusIn(String accountNumber, List<RequestStatus> statuses);
}
