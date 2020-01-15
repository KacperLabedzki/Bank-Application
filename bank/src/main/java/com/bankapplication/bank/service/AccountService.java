package com.bankapplication.bank.service;

import com.bankapplication.bank.model.Account;
import com.bankapplication.bank.model.Customer;
import com.bankapplication.bank.repository.AccountRepository;
import com.bankapplication.bank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    public AccountService(AccountRepository accountRepository,CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public Account addAccount(long customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Account account = new Account();
        account.setAccountOwner(optionalCustomer.get());
        return accountRepository.save(account);
    }
    public void deleteAccount(long accountId){
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        accountRepository.delete(optionalAccount.get());
    }
    public Account addMoneyToAccount(long accountId, BigDecimal amount){
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        BigDecimal balance = optionalAccount.get().getBalance();
        balance = balance.add(amount);
        optionalAccount.get().setBalance(balance);
        return accountRepository.save(optionalAccount.get());
    }


}
