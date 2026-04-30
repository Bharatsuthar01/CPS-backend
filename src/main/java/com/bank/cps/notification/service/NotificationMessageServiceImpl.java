package com.bank.cps.notification.service;

import com.bank.cps.notification.document.NotificationMessageDocument;
import com.bank.cps.notification.dto.SaveNotificationMessageRequest;
import com.bank.cps.notification.repository.NotificationMessageRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class NotificationMessageServiceImpl implements NotificationMessageService {
    private final NotificationMessageRepository repository;

    public NotificationMessageServiceImpl(NotificationMessageRepository repository) {
        this.repository = repository;
    }

    public NotificationMessageDocument save(SaveNotificationMessageRequest request) {
        NotificationMessageDocument doc = repository.findByCode(request.code())
                .orElseGet(NotificationMessageDocument::new);
        if (doc.getId() == null)
            doc.setId(UUID.randomUUID().toString());
        doc.setCode(request.code());
        doc.setName(request.name());
        doc.setDescription(request.description());
        doc.setStatus("ACTIVE");
        return repository.save(doc);
    }

    public List<NotificationMessageDocument> findAll() {
        return repository.findAll();
    }
}
