package org.example.wigell_padel.services;

import org.example.wigell_padel.entities.Customer;
import org.example.wigell_padel.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.log4j.Logger;

@Service
public class CustomerService implements CustomerServiceInterface {

    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Customer save(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        logger.info("Customer saved: " + savedCustomer.getName());
        return savedCustomer;
    }

    @Override
    public Customer fetchCustomerByUserName(String userName) {
        return customerRepository.findByUsername(userName);
    }

    @Override
    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }
}
