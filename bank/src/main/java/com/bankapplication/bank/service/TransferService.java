package com.bankapplication.bank.service;

import com.bankapplication.bank.model.Account;
import com.bankapplication.bank.model.Transfer;
import com.bankapplication.bank.repository.AccountRepository;
import com.bankapplication.bank.repository.TransferRepository;
import com.bankapplication.bank.validators.BalanceValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {
    private TransferRepository transferRepository;
    private AccountRepository accountRepository;

    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public Transfer sendTransfer(Transfer transfer) {
        Optional<Account> accountFrom = accountRepository.findByNrb(transfer.getNrbFrom());
        Optional<Account> accountTo = accountRepository.findByNrb(transfer.getNrbTo());
        BigDecimal accountFromBalance = accountFrom.get().getBalance();
        BigDecimal accountToBalance = accountTo.get().getBalance();
        BalanceValidator balanceValidator = new BalanceValidator(accountFrom.get(), transfer.getAmount());
        if (balanceValidator.isValid()) {
            accountFrom.get().setBalance(accountFromBalance.subtract(transfer.getAmount()));
            accountTo.get().setBalance(accountToBalance.add(transfer.getAmount()));
            accountRepository.save(accountFrom.get());
            accountRepository.save(accountTo.get());
            return transferRepository.save(transfer);
        }
        return null;
    }

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }
}
