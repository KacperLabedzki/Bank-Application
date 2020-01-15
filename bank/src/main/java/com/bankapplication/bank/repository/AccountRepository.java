package com.bankapplication.bank.repository;

import com.bankapplication.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account>findById(long idAccount);
}