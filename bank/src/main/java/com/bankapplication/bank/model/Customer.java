package com.bankapplication.bank.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customers")

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String phoneNumber;

    @Basic
    private java.sql.Date dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

}
