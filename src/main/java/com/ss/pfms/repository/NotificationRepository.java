package com.ss.pfms.repository;

import com.ss.pfms.entity.Notification;
import com.ss.pfms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUserAndReadFalse(User user);
}
