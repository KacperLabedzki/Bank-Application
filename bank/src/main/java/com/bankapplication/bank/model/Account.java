package com.bankapplication.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Random;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String nrRef;
    private String nrb;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private Customer accountOwner;

    public Account(long id, String nrRef, String nrb, BigDecimal balance, Customer accountOwner) {
        this.id = id;
        this.nrRef = nrRef;
        this.nrb = nrb;
        this.balance = balance;
        this.accountOwner = accountOwner;
    }

    public Account() {
        this.nrRef = nrRefGenerator();
        this.nrb = accountNumberGenerator();
        this.balance = new BigDecimal(0);
    }

    private String accountNumberGenerator() {
        return numberGenerator(26);
    }

    private String nrRefGenerator() {
        return "ROR-" + numberGenerator(5);
    }

    private String numberGenerator(int howLong) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < howLong; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
