package com.bankapplication.bank.validators;

public class EmailValidator implements Validator{
    private String email;

    public EmailValidator(String email) {
        this.email = email;
    }


    @Override
    public boolean isValid() {

        return false;
    }
}
