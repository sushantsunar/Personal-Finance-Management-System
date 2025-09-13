package com.ss.pfms.controller;

import com.ss.pfms.entity.Transaction;
import com.ss.pfms.entity.User;
import com.ss.pfms.service.TransactionService;
import com.ss.pfms.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import com.ss.pfms.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Fetch the User entity using the username (which is email)
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (user == null) {
            // handle error, e.g. redirect to login
            return "redirect:/login";
        }

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

    @PostMapping("/dashboard/notifications/read/{id}")
    public String markNotificationAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return "redirect:/dashboard";
    }
}