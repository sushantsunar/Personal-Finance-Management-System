package com.ss.pfms.service;

import com.ss.pfms.entity.Budget;
import com.ss.pfms.entity.User;
import java.util.List;

public interface BudgetService {
    Budget saveBudget(Budget budget);
    List<Budget> getBudgetsByUser(User user);
}