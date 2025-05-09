package com.personalbudgettracker.budgettracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.personalbudgettracker.budgettracker.model.Expense;
import com.personalbudgettracker.budgettracker.service.ExpenseService;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found with ID: " + id);
        }
        return ResponseEntity.ok(expense);
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(Authentication authentication,@RequestBody Expense expense) {
        try {
            String email = authentication.getName(); 
            Expense savedExpense = expenseService.saveExpense(email,expense);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Expense Data", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        if (expense == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found with ID: " + id);
        }
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}