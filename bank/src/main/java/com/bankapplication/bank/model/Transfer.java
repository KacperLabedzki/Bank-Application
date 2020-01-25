package com.bankapplication.bank.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String senderEmail;
    private String nrbFrom;
    private String nrbTo;
    private BigDecimal amount;
    @Basic
    private Date date;

    public Transfer(long id, String nrbFrom, String nrbTo, BigDecimal amount, Date date) {
        this.id = id;
        this.nrbFrom = nrbFrom;
        this.nrbTo = nrbTo;
        this.amount = amount;
        this.date = date;
    }

    public Transfer() {
    }

}
