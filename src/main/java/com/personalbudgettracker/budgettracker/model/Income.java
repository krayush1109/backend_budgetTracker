package com.personalbudgettracker.budgettracker.model;
 
import jakarta.persistence.*;
import java.util.Date;
 
@Entity
public class Income {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incomeId;
 
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
 
    private Double amount;
 
    private String description;
 
    @Column(nullable = false)
    private Date date;
 
    private Double budget;
 
  
    public Income() {
    }
 
   
    public Income(User user, Double amount, String description, Date date, Double budget) {
        this.user = user;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.budget = budget;
    }
 

    public Long getIncomeId() {
        return incomeId;
    }
 
    public void setIncomeId(Long incomeId) {
        this.incomeId = incomeId;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
 
    public Double getAmount() {
        return amount;
    }
 
    public void setAmount(Double amount) {
        this.amount = amount;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public Date getDate() {
        return date;
    }
 
    public void setDate(Date date) {
        this.date = date;
    }
 
    public Double getBudget() {
        return budget;
    }
 
    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
 