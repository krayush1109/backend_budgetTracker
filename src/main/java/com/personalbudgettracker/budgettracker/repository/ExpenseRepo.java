package com.personalbudgettracker.budgettracker.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalbudgettracker.budgettracker.model.Expense;
import com.personalbudgettracker.budgettracker.model.Income;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {   
    List<Expense> findByUserUserId(Long userId);
} 