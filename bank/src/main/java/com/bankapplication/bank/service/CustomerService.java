package com.bankapplication.bank.service;

import com.bankapplication.bank.exceptinos.BadRequestException;
import com.bankapplication.bank.model.Customer;
import com.bankapplication.bank.repository.CustomerRepository;
import com.bankapplication.bank.validators.EmailValidator;
import com.bankapplication.bank.validators.PeselValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(long idCustomer) {
        return customerRepository.findById(idCustomer).get();
    }

    public Customer addCustomer(Customer customer) {
        PeselValidator peselValidator = new PeselValidator(customer.getPesel());
        EmailValidator emailValidator = new EmailValidator(customer.getEmail());
        if (peselValidator.isValid() && emailValidator.isValid()) {
            return customerRepository.save(customer);
        }
        return null;
    }

    public Customer updateCustomer(Customer customer, long idCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(idCustomer);
        if (optionalCustomer.isPresent()) {
            optionalCustomer.get().setFirstName(customer.getFirstName());
            optionalCustomer.get().setLastName(customer.getLastName());
            optionalCustomer.get().setEmail(customer.getEmail());
            optionalCustomer.get().setPhoneNumber(customer.getPhoneNumber());
            return customerRepository.save(optionalCustomer.get());
        }
        throw new BadRequestException("A client with this id does not exist");
    }

    public void deleteCustomer(long idCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(idCustomer);
        if (optionalCustomer.isPresent()) {
            customerRepository.delete(optionalCustomer.get());
        } else {
            throw new BadRequestException("A client with this id does not exist");
        }

    }
}
