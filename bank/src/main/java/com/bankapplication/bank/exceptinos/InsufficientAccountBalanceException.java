package com.bankapplication.bank.exceptinos;

public class InsufficientAccountBalanceException extends RuntimeException {
    public InsufficientAccountBalanceException() {
        super("Insufficient funds on account");
    }
}
