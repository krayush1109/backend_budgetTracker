package com.personalbudgettracker.budgettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalbudgettracker.budgettracker.model.Income;
@Repository
public interface IncomeRepo extends JpaRepository<Income, Long>{
    

}