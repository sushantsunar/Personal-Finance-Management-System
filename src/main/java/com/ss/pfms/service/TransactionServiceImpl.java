package com.ss.pfms.service;

import com.ss.pfms.entity.Transaction;
import com.ss.pfms.entity.Budget;
import com.ss.pfms.entity.User;
import com.ss.pfms.repository.TransactionRepository;
import com.ss.pfms.repository.BudgetRepository;
import com.ss.pfms.service.TransactionService;
import com.ss.pfms.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public Transaction addTransaction(Transaction t) {
        Transaction saved = transactionRepository.save(t);

        if ("EXPENSE".equalsIgnoreCase(t.getType())) {
            int month = t.getDate().getMonthValue();
            int year = t.getDate().getYear();
            List<Budget> budgets = budgetRepository.findByUserAndMonthAndYear(t.getUser(), month, year);

            for (Budget b : budgets) {
                if (b.getCategory().equalsIgnoreCase(t.getCategory())) {
                    double totalSpent = transactionRepository.findByUser(t.getUser())
                            .stream()
                            .filter(tx -> "EXPENSE".equalsIgnoreCase(tx.getType()) &&
                                    tx.getCategory().equalsIgnoreCase(b.getCategory()))
                            .mapToDouble(Transaction::getAmount)
                            .sum();

                    if (totalSpent > b.getLimitAmount()) {
                        notificationService.createNotification(
                                t.getUser(),
                                "Alert! You have exceeded your budget for " + b.getCategory()
                        );
                    }
                }
            }
        }

        return saved;
    }

    @Override
    public List<Transaction> getAllTransactions(User user) {
        return transactionRepository.findByUser(user);
    }
}