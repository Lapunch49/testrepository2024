package ru.shop.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ru.shop.Entities.Customer;
import ru.shop.Entities.Order;
import ru.shop.Entities.Product;
import ru.shop.Interfaces.Service;
import ru.shop.Repositories.OrderRepository;

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
        List<Order> newOrders = new ArrayList<Order>();
        for (Order order : orderRepository.orders) {
            if (order.customerId() == customerId) {
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
