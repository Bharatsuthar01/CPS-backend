package com.bank.cps.notification.repository;

import com.bank.cps.notification.document.NotificationMessageDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationMessageRepository extends MongoRepository<NotificationMessageDocument, String> {
    Optional<NotificationMessageDocument> findByCode(String code);
}
