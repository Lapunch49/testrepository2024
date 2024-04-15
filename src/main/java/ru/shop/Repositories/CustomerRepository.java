package ru.shop.Repositories;

import java.util.ArrayList;
import java.util.List;
import ru.shop.Entities.Customer;
import ru.shop.Interfaces.Repository;

public class CustomerRepository implements Repository<Customer> {
    List<Customer> customers = new ArrayList<>();

    public void save(Customer customer){
        customers.add(customer);
    }
    public List<Customer> findAll(){
        return new ArrayList<Customer>(customers);
    }
}
