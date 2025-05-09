package com.personalbudgettracker.budgettracker.model;

public class AuthResponse {
    private String token;
    private Long userId;
    private String username;

    // ✅ Updated constructor to accept all three values
    public AuthResponse(String token, Long userId, String username) {
        this.token = token;
        this.userId = userId;
        this.username = username;
    }

    // ✅ Getters to access stored values
    public String getToken() { return token; }
    public Long getUserId() { return userId; }
    public String getUsername() { return username; }

    // ✅ (Optional) Setters if needed
    public void setToken(String token) { this.token = token; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setUsername(String username) { this.username = username; }
}