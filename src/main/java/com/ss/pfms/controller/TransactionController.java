package com.ss.pfms.controller;

import com.ss.pfms.entity.Transaction;
import com.ss.pfms.entity.User;
import com.ss.pfms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public String transactions(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactions", transactionService.getAllTransactions(user));
        return "transactions";
    }

    @PostMapping("/transactions")
    public String addTransaction(@ModelAttribute Transaction transaction, @AuthenticationPrincipal User user) {
        transaction.setUser(user);
        transactionService.addTransaction(transaction);
        return "redirect:/transactions";
    }
}