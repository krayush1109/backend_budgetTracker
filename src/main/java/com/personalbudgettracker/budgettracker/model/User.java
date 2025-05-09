package com.personalbudgettracker.budgettracker.model;
 
import java.time.LocalDateTime;
import java.util.List;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
 
@Entity
public class User {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
 
    @Column(nullable = false, unique = true)
    private String username;
 
    @Column(nullable = false)
    private String password;
 
    @Column(nullable = false, unique = true)
    private String email;
 
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
 
    @OneToMany
    private List<Expense> expense;
 
     @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
     private Income income;
   
 
    public User() {}
 
    public User(String username, String password, String email, LocalDateTime createdAt) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }
 
    public Long getUserId() {
        return userId;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
 
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}