package ru.shop.services;

import java.util.List;

import ru.shop.entity.Customer;
import ru.shop.repositories.CustomerRepository;

public class CustomerService implements Service<Customer> {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public void save(Customer customer){
        customerRepository.save(customer);
    }
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
}
