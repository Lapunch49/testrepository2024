package ru.shop.Repositories;

import java.util.ArrayList;
import java.util.List;
import ru.shop.Entities.Order;
import ru.shop.Interfaces.Repository;


public class OrderRepository implements Repository<Order> {
    public List<Order> orders = new ArrayList<>();

    public void save(Order order){
        orders.add(order);
    }
    public List<Order> findAll(){
        return new ArrayList<Order>(orders);
    }
    public int getCount(){
        return orders.size();
    }
}