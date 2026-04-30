package com.bank.cps.notification.service;

import com.bank.cps.notification.document.NotificationMessageDocument;
import com.bank.cps.notification.dto.SaveNotificationMessageRequest;
import java.util.List;

public interface NotificationMessageService {
    NotificationMessageDocument save(SaveNotificationMessageRequest request);
    List<NotificationMessageDocument> findAll();
}
