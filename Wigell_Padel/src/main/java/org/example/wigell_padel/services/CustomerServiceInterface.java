package org.example.wigell_padel.services;

import org.example.wigell_padel.entities.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    Customer save(Customer customer);
    Customer fetchCustomerByUserName(String userName);
    List<Customer> fetchAllCustomers();
}
