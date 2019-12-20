package com.bankapplication.bank.controller;

import com.bankapplication.bank.model.Customer;
import com.bankapplication.bank.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping(ENDPOINT+"/{idCustomer}")
    public ResponseEntity getCustomer(@PathVariable long idCustomer){
        return new ResponseEntity(customerService.getCustomer(idCustomer), HttpStatus.OK);
    }

    @PostMapping(ENDPOINT)
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return new ResponseEntity<Customer>(customerService.addCustomer(customer),HttpStatus.OK);
    }

    @PutMapping(ENDPOINT+"/{idCustomer}")
    public ResponseEntity<Customer>updateCustomer(@PathVariable long idCustomer,@RequestBody Customer customer){
        if(customer!=null){
            return new ResponseEntity<Customer>(customerService.updateCustomer(customer,idCustomer),HttpStatus.OK);
        }
        else
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(ENDPOINT+"/{idCustomer}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable long idCustomer){
        customerService.deleteCustomer(idCustomer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
