package com.personalbudgettracker.budgettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.personalbudgettracker.budgettracker.model.Income;
@Repository
public interface IncomeRepo extends JpaRepository<Income, Long>{
    @Query("SELECT i FROM Income i WHERE i.user.userId = :userId")
    Income findByUserUserId(Long userId);
}