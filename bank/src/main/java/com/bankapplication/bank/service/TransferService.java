package com.bankapplication.bank.service;

import com.bankapplication.bank.model.Account;
import com.bankapplication.bank.model.StatusCode;
import com.bankapplication.bank.model.Transfer;
import com.bankapplication.bank.model.TransferStatus;
import com.bankapplication.bank.repository.AccountRepository;
import com.bankapplication.bank.repository.TransferRepository;
import com.bankapplication.bank.response.TransferStatusResponse;
import com.bankapplication.bank.validators.BalanceValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {
    private TransferRepository transferRepository;
    private AccountRepository accountRepository;
    private StatusCode statusCode;

    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
    }

    public TransferStatusResponse sendTransfer(Transfer transfer) {
        Optional<Account> accountFrom = accountRepository.findByNrb(transfer.getNrbFrom());
        Optional<Account> accountTo = accountRepository.findByNrb(transfer.getNrbTo());
        BigDecimal accountFromBalance = accountFrom.get().getBalance();
        BigDecimal accountToBalance = accountTo.get().getBalance();
        BalanceValidator balanceValidator = new BalanceValidator(accountFrom.get(), transfer.getAmount());
        if (balanceValidator.isValid()) {
            try {
                statusCode = StatusCode.OK;
                accountFrom.get().setBalance(accountFromBalance.subtract(transfer.getAmount()));
                accountTo.get().setBalance(accountToBalance.add(transfer.getAmount()));
                accountRepository.save(accountFrom.get());
                accountRepository.save(accountTo.get());
                transferRepository.save(transfer);
                return new TransferStatusResponse(TransferStatus.SUCCESS, statusCode.getCode(), new Date());
            } catch (Exception e) {
                statusCode = StatusCode.NOT_FOUND;
                return new TransferStatusResponse(TransferStatus.UNSUCCESSFUL, statusCode.getCode(), new Date());
            }
        }
        return null;
    }

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }
}
