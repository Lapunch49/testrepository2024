package ru.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import ru.shop.entity.Customer;
import ru.shop.entity.Order;
import ru.shop.entity.Product;
import ru.shop.repositories.OrderRepository;

public class OrderService implements Service<Order> {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public static class BadOrderCountException extends Exception { // создаём своё проверяемое исключение
        public BadOrderCountException() {
            super("Количество товара меньше или равно 0!");
        }
    }
    public void add(Customer customer, Product product, long count) throws BadOrderCountException {
        if (count < 0 ) {
            throw new BadOrderCountException();
        } else{
            UUID uuid = UUID.randomUUID();
            orderRepository.save(new Order(uuid.toString(), customer.getCustomerId(), product.getProductId(), count, count*product.cost()));
        }
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    public List<Order> findByCustomer(String customerId){
        List<Order> newOrders = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            if (Objects.equals(order.customerId(), customerId)) {
                newOrders.add(order);
            }
        }
        return newOrders;
    }
    public long getTotalCustomerAmount(String customerId){
        List<Order> ordersByCustomer = findByCustomer(customerId);
        long total = 0;
        for (Order order : ordersByCustomer) {
            total += order.amount();
        }
        return total;
    }

}
