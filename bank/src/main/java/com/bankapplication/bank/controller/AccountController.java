package com.bankapplication.bank.controller;

import com.bankapplication.bank.model.Account;
import com.bankapplication.bank.model.Customer;
import com.bankapplication.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("/bankapi/")
public class AccountController {
    private AccountService accountService;
    private final String ENDPOINT = "/account";

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping(ENDPOINT + "/{customerId}")
    public ResponseEntity<Account> addCustomer(@PathVariable long customerId) {
        return new ResponseEntity<Account>(accountService.addAccount(customerId), HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT + "/{accountId}")
    public ResponseEntity<Account> deleteAccount(@PathVariable long accountId) {
        try {
            accountService.deleteAccount(accountId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<Account> addMoney(long accountId, BigDecimal amount) {
        accountService.addMoneyToAccount(accountId, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
