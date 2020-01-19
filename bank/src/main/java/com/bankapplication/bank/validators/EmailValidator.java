package com.bankapplication.bank.validators;

import com.bankapplication.bank.exceptinos.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private String email;
    private Pattern pattern;
    private Matcher matcher;

    public EmailValidator(String email) {
        this.email = email;
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean isValid() {
        matcher = pattern.matcher(email);
        if(matcher.matches()){
            return true;
        }else {
            throw new BadRequestException("Bledny email");
        }
    }
}
