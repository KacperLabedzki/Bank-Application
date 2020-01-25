package com.bankapplication.bank.validators;

import com.bankapplication.bank.model.Account;

import java.math.BigDecimal;

public class BalanceValidator implements Validator {
    private Account account;
    private BigDecimal amount;

    public BalanceValidator(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public boolean isValid() {
        if (account.getBalance().compareTo(amount) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
