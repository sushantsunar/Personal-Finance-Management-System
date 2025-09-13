package com.ss.pfms.service;

import com.ss.pfms.entity.Notification;
import com.ss.pfms.entity.User;
import com.ss.pfms.repository.NotificationRepository;
import com.ss.pfms.service.NotificationService;
import com.ss.pfms.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void createNotification(User user, String message) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notificationRepository.save(notification);

        // Send email alert
        emailService.sendEmail(user.getEmail(), "Finance Alert", message);
    }

    @Override
    public List<Notification> getUnreadNotifications(User user) {
        return notificationRepository.findByUserAndReadFalse(user);
    }

    @Override
    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    public void sendWelcomeEmail(User user) {
        // Use user.getEmail() safely now
        String email = user.getEmail();
        String name = user.getFirstName();

        // Simple print to test
        System.out.println("Sending welcome email to " + name + " at " + email);

        // Here you can integrate actual email sending logic
    }
}