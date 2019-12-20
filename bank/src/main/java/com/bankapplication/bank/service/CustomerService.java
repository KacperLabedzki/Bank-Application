package com.bankapplication.bank.service;

import com.bankapplication.bank.model.Customer;
import com.bankapplication.bank.repository.CustomerRepository;
import com.bankapplication.bank.validators.PeselValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomer(long idCustomer){
        return customerRepository.findById(idCustomer).get();
    }

    public Customer addCustomer(Customer customer){
        PeselValidator peselValidator = new PeselValidator(customer.getPesel());
        if(peselValidator.isValid()){
            return customerRepository.save(customer);
        }else {
            throw new IllegalArgumentException();
        }
    }

    public Customer updateCustomer (Customer customer, long idCustomer){
        Optional<Customer> optionalCustomer = customerRepository.findById(idCustomer);
        if(optionalCustomer.isPresent()){
            optionalCustomer.get().setFirstName(customer.getFirstName());
            optionalCustomer.get().setLastName(customer.getLastName());
            optionalCustomer.get().setEmail(customer.getEmail());
            optionalCustomer.get().setPhoneNumber(customer.getPhoneNumber());
            return customerRepository.save(optionalCustomer.get());
        }
        return null;
    }
    public void deleteCustomer(long idCustomer){
        Optional<Customer> optionalCustomer = customerRepository.findById(idCustomer);
        customerRepository.delete(optionalCustomer.get());
    }

}
