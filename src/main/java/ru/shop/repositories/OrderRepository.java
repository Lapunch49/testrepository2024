package ru.shop.repositories;

import java.util.ArrayList;
import java.util.List;
import ru.shop.entity.Order;


public class OrderRepository implements Repository<Order> {
    List<Order> orders = new ArrayList<>();

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