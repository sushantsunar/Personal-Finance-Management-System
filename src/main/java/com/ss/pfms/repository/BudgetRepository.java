package com.ss.pfms.repository;

import com.ss.pfms.entity.Budget;
import com.ss.pfms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BudgetRepository extends JpaRepository<Budget, Long>{
    List<Budget> findByUserAndMonthAndYear(User user, int month, int year);
}
