package com.personalbudgettracker.budgettracker.service;
 
import java.util.List;
 
import com.personalbudgettracker.budgettracker.model.Income;
 
public interface IncomeService {
    Income addIncome(String email,Income income);
    List<Income> getAllIncomes();
    Income getIncomeById(Long incomeId);
    Income updateIncome(String email,Long incomeId, Income updatedIncome);
    void deleteIncome(Long incomeId);  
}