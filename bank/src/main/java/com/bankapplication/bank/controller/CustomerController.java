package com.bankapplication.bank.controller;

import com.bankapplication.bank.model.Customer;
import com.bankapplication.bank.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/bankapi/")
public class CustomerController {
    private CustomerService customerService;
    private static final String ENDPOINT = "/customers";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(ENDPOINT)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping(ENDPOINT + "/{idCustomer}")
    public ResponseEntity getCustomer(@PathVariable long idCustomer) {
        return new ResponseEntity(customerService.getCustomer(idCustomer), HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<Customer>(customerService.addCustomer(customer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(ENDPOINT + "/{idCustomer}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long idCustomer, @RequestBody Customer customer) {
            return new ResponseEntity<Customer>(customerService.updateCustomer(customer, idCustomer), HttpStatus.OK);
    }

    @DeleteMapping(ENDPOINT + "/{idCustomer}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable long idCustomer) {
        try {
            customerService.deleteCustomer(idCustomer);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
