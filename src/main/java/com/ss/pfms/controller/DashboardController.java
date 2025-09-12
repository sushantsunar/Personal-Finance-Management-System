package com.ss.pfms.controller;

import com.ss.pfms.entity.Transaction;
import com.ss.pfms.entity.User;
import com.ss.pfms.service.TransactionService;
import com.ss.pfms.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal User user) {
        List<Transaction> transactions = transactionService.getAllTransactions(user);

        double totalIncome = transactions.stream()
                .filter(t -> "INCOME".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount).sum();

        double totalExpense = transactions.stream()
                .filter(t -> "EXPENSE".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount).sum();

        double savings = totalIncome - totalExpense;

        Map<String, Double> categoryMap = transactions.stream()
                .filter(t -> "EXPENSE".equalsIgnoreCase(t.getType()))
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));

        model.addAttribute("user", user);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("savings", savings);
        model.addAttribute("categories", categoryMap.keySet());
        model.addAttribute("categoryAmounts", categoryMap.values());
        model.addAttribute("notifications", notificationService.getUnreadNotifications(user));

        return "dashboard";
    }

    @PostMapping("/notifications/read/{id}")
    public String markNotificationAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return "redirect:/dashboard";
    }
}