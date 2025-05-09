package com.personalbudgettracker.budgettracker.exceptions;

public class NoIncomesFoundException extends RuntimeException {
    public NoIncomesFoundException(String message) {
        super(message);
    }
}
