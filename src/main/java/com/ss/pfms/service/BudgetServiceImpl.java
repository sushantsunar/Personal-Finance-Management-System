package com.ss.pfms.service;

import com.ss.pfms.entity.Budget;
import com.ss.pfms.entity.User;
import com.ss.pfms.repository.BudgetRepository;
import com.ss.pfms.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> getBudgetsByUser(User user) {
        return budgetRepository.findByUserAndMonthAndYear(user,
                java.time.LocalDate.now().getMonthValue(),
                java.time.LocalDate.now().getYear());
    }
}