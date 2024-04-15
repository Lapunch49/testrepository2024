package ru.shop;

import java.util.*;

import ru.shop.Entities.Customer;
import ru.shop.Entities.Order;
import ru.shop.Entities.Product;
import ru.shop.Entities.ProductType;
import ru.shop.Repositories.ProductRepository;
import ru.shop.Repositories.CustomerRepository;
import ru.shop.Repositories.OrderRepository;
import ru.shop.Services.CustomerService;
import ru.shop.Services.OrderService;
import ru.shop.Services.ProductService;

public class Main {
    public static <Map> void main(String[] args) {

    ProductRepository productRepo = new ProductRepository();
    ProductService productService = new ProductService(productRepo);
    List<Product> listOfProducts = new ArrayList<>();
    listOfProducts.add(new Product("001", "sofa", 100_000, ProductType.GOOD));
    listOfProducts.add(new Product("002", "chair", 10_000, ProductType.GOOD));
    listOfProducts.add(new Product("003", "computer", 150_000, ProductType.GOOD));
    listOfProducts.add(new Product("004", "phone", 60_000, ProductType.GOOD));
    listOfProducts.add(new Product("100", "some service", 10_000, ProductType.SERVICE));

    for (var productL : listOfProducts){
        productService.save(productL);
    }

    CustomerRepository customerRepo = new CustomerRepository();
    CustomerService customerService = new CustomerService(customerRepo);
    List<Customer> listOfCustomer = new ArrayList<>();
    listOfCustomer.add(new Customer("001", "Vadim", "89373343076", 56));
    listOfCustomer.add(new Customer("002", "Lena", "89373343076", 24));
    listOfCustomer.add(new Customer("003", "Rosa", "89373343076", 45));
    listOfCustomer.add(new Customer("004", "Ivan", "89373343076", 26));

    for (var customerL : listOfCustomer){
        customerService.save(customerL);
    }

    OrderRepository orderRepo = new OrderRepository();
    OrderService orderService = new OrderService(orderRepo);
    try{
        orderService.add(listOfCustomer.get(0), listOfProducts.get(0), 2);
        orderService.add(listOfCustomer.get(1), listOfProducts.get(1), 10);
        orderService.add(listOfCustomer.get(2), listOfProducts.get(2), 3);
        orderService.add(listOfCustomer.get(0), listOfProducts.get(3), 26);
        orderService.add(listOfCustomer.get(0), listOfProducts.get(4), -4);
    } catch(OrderService.BadOrderCountException e){
        System.out.println(e.getMessage());
    }

        //  * Суммы для оплаты в разрезе по заказчикам
        // Количество заказчиков
    System.out.println("\nКоличество заказчиков: " + customerService.findAll().size());
        // Количество заказов всего и в разрезе по типам
    System.out.println("\nКоличество продуктов всего: " + productService.findAll().size());
    System.out.println("Количество продуктов в разрезе по типам:GOOD: " + productService.findByProductType(ProductType.GOOD).size());
    System.out.println("Количество продуктов в разрезе по типам:SERVICE: " + productService.findByProductType(ProductType.SERVICE).size());
        // Количество заказов всего и в разрезе по заказчикам
    System.out.println("\nКоличество заказов всего: " + orderService.findAll().size());
    System.out.println("Количество заказов в разрезе по заказчикам: ");
    List<Order> orders = orderService.findAll();
    java.util.Map<String, Long> ordersByCustomers = new HashMap<>();
    for (var order: orders){
        var count =ordersByCustomers.get(order.customerId());
        if (count == null){
            ordersByCustomers.put(order.customerId(), 1L);
        } else{
            ordersByCustomers.put(order.customerId(), ++count);
        }
    }
    for(java.util.Map.Entry<String, Long> item : ordersByCustomers.entrySet()){

        System.out.printf("Заказчик с ID=%s: Количество заказов=%d \n", item.getKey(), item.getValue());
    }
        // Суммы для оплаты в разрезе по заказчикам
    System.out.printf("\nСуммы для оплаты в разрезе по заказчикам:\n");
    for (var customer: listOfCustomer){
        var customerId = customer.getCustomerId();
        System.out.printf("Заказчик с ID=%s: Сумма для оплаты=%d \n", customerId,orderService.getTotalCustomerAmount(customerId));
    }
    }
}

