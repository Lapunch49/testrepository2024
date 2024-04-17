package ru.shop.repositories;

import java.util.ArrayList;
import java.util.List;
import ru.shop.entity.Customer;

public class CustomerRepository implements Repository<Customer> {
    List<Customer> customers = new ArrayList<>();

    public void save(Customer customer){
        customers.add(customer);
    }
    public List<Customer> findAll(){
        return new ArrayList<Customer>(customers);
    }
}
