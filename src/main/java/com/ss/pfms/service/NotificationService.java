package com.ss.pfms.service;

import com.ss.pfms.entity.Notification;
import com.ss.pfms.entity.User;
import java.util.List;

public interface NotificationService {
    void createNotification(User user, String message);
    List<Notification> getUnreadNotifications(User user);
    void markAsRead(Long id);
}