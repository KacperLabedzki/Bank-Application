package com.bankapplication.bank.service;

import com.bankapplication.bank.model.Address;
import com.bankapplication.bank.model.Customer;
import com.bankapplication.bank.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {
    private List<Customer> customers = new ArrayList<>();
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init() {

        customer1 = new Customer(((long) 1), "Ania", "Kolek", "70051749977", "ania@gmail.com", "724023123", (Date.valueOf("1998-01-21")), new Address((long) 1, "Klonowa", "Lodz", "32-100"));
        customer2 = new Customer(((long) 2), "Arek", "Nowak", "54011487255", "nowak@gmail.com", "543432754", (Date.valueOf("1992-06-12")), new Address((long) 2, "Zielona", "Warszawa", "10-101"));
        customer3 = new Customer(((long) 3), "Teresa", "Kowalska", "67111977397", "kowalska@gmail.com", "732521853", (Date.valueOf("1989-12-11")), new Address((long) 3, "Morska", "Gdynia", "52-253"));

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
    }

    @Test
    void getAllCustomers() {
        when(customerRepository.findAll()).thenReturn(customers);
        assertEquals(3, customerService.getAllCustomers().size());
    }

    @Test
    void getCustomer() {
        when(customerRepository.findById(1)).thenReturn(java.util.Optional.of(customer1));
        Customer customer = customerService.getCustomer(1);

        assertEquals(1, customer.getId());
        assertEquals("Ania", customer.getFirstName());
        assertEquals("Kolek", customer.getLastName());
        assertEquals("70051749977", customer.getPesel());
        assertEquals("ania@gmail.com", customer.getEmail());
        assertEquals("724023123", customer.getPhoneNumber());
        assertEquals(Date.valueOf("1998-01-21"), customer.getDateOfBirth());
        assertEquals(new Address((long) 1, "Klonowa", "Lodz", "32-100"), customer.getAddress());
    }

    @Test
    void addCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }

}