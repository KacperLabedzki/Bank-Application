package com.bankapplication.bank.exceptinos;

public class InsufficientAccountBalanceException extends RuntimeException {
    public InsufficientAccountBalanceException() {
        super("Brak wystarczajacych srodkow na koncie");
    }
}
