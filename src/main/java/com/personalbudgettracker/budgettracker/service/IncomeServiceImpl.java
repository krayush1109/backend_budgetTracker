package com.personalbudgettracker.budgettracker.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.personalbudgettracker.budgettracker.exceptions.IncomeNotFoundException;
import com.personalbudgettracker.budgettracker.exceptions.NoIncomesFoundException;
import com.personalbudgettracker.budgettracker.model.Income;
import com.personalbudgettracker.budgettracker.model.User;
import com.personalbudgettracker.budgettracker.repository.IncomeRepo;
import com.personalbudgettracker.budgettracker.repository.UserRepo;
 
@Service
public class IncomeServiceImpl implements IncomeService{
    @Autowired
    private IncomeRepo incomeRepo;
 
    @Autowired
    private UserRepo userRepo;
 
    @Override
    public Income addIncome(String email, Income income) {
        
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        User user = userOptional.get();
        income.setUser(user); 
        return incomeRepo.save(income); 
    }

    
 
  @Override
public List<Income> getAllIncomes() throws NoIncomesFoundException {
    List<Income> incomes = incomeRepo.findAll();
    if (incomes.isEmpty()) {
        throw new NoIncomesFoundException("No incomes found");
    }
    return incomes;
}
 
 
    public Income getIncomeById(Long incomeId) throws IncomeNotFoundException {
        Optional<Income> incomeOpt = incomeRepo.findById(incomeId);
        if (incomeOpt.isPresent()) {
            return incomeOpt.get();
        } else {
            throw new IncomeNotFoundException("Income with ID " + incomeId + " not found");
        }
    }
   
   
 
    public Income updateIncome(String email, Long incomeId, Income updatedIncome) throws IncomeNotFoundException {
        // ✅ Find user by email (from JWT)
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Find income by ID
        Income existingIncome = incomeRepo.findById(incomeId)
                .orElseThrow(() -> new IncomeNotFoundException("Income with ID " + incomeId + " not found"));

        // ✅ Ensure the authenticated user owns this income record
        if (!existingIncome.getUser().getUserId().equals(user.getUserId())) {
            throw new SecurityException("Unauthorized to update this income record"); // 🚨 Prevent unauthorized updates
        }
        existingIncome.setAmount(updatedIncome.getAmount());
        existingIncome.setDescription(updatedIncome.getDescription());
        existingIncome.setDate(updatedIncome.getDate());
        existingIncome.setBudget(updatedIncome.getBudget());

        return incomeRepo.save(existingIncome); // ✅ Save updated income
    }
   

   
 
    @Override
    public void deleteIncome(Long incomeId) throws IncomeNotFoundException {
        if (incomeRepo.existsById(incomeId)) {
            incomeRepo.deleteById(incomeId);
        } else {
            throw new IncomeNotFoundException("Income with ID " + incomeId + " not found");
        }
    }
   
   
}