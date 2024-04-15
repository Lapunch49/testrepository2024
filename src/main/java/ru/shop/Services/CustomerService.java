package ru.shop.Services;

import java.util.List;

import ru.shop.Entities.Customer;
import ru.shop.Interfaces.Service;
import ru.shop.Repositories.CustomerRepository;

public class CustomerService implements Service<Customer> {
    CustomerRepository customerRepository;
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
