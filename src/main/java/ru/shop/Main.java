package ru.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

enum ProductType {GOOD, SERVICE}

record Product(String id, String name, long cost, ProductType productType) {
    public String getProductId(){
        return id;
    }
    public ProductType getProductType(){
        return productType;
    }
}

record Customer(String id, String name, String phone, long age) {
    public String getCustomerId(){
        return id;
    }
}

record Order(String id, String customerId, String productId, long count, long amount) {
}

class ProductRepository{
    List<Product> products = new ArrayList<>();

    public void save(Product product){
        products.add(product);
    }
    List<Product> findAll(){
        ArrayList<Product> products_copy = new ArrayList<Product>(products);
        return products_copy;
    }
}

class OrderRepository{
    List<Order> orders = new ArrayList<>();

    public void save(Order order){
        orders.add(order);
    }
    List<Order> findAll(){
        return new ArrayList<Order>(orders);
    }
    public int getCount(){
        return orders.size();
    }
}

class CustomerRepository{
    List<Customer> customers = new ArrayList<>();

    public void save(Customer customer){
        customers.add(customer);
    }
    List<Customer> findAll(){
        return new ArrayList<Customer>(customers);
    }
}
class ProductService{
    ProductRepository productRepository;
    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public void save(Product product){
        productRepository.save(product);
    }
    List<Product> findAll(){
        return productRepository.findAll();
    }
    List<Product> findByProductType(ProductType productType){
        List<Product> productsCopy = productRepository.findAll();
        for (Product product : productsCopy) {
            if (product.getProductType() != productType) {
                productsCopy.remove(product);
            }
        }
        return productsCopy;
    }
}

class CustomerService{
    CustomerRepository customerRepository;
    CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public void save(Customer customer){
        customerRepository.save(customer);
    }
    List<Customer> findAll(){
        return customerRepository.findAll();
    }
}
class OrderService{
    private final OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public static class BadOrderCountException extends Exception { // создаём своё проверяемое исключение
        public BadOrderCountException() {
            super("Количество товара меньше или равно 0!");
        }
    }
    public void add(Customer customer, Product product, long count) throws BadOrderCountException {
        Long countL;
        if (count < 0 ) {
            throw new BadOrderCountException();
        } else{
            UUID uuid = UUID.randomUUID();
            orderRepository.save(new Order(uuid.toString(), customer.getCustomerId(), product.getProductId(), count, count*product.cost()));
        }
    }

    List<Order> findAll(){
        return orderRepository.findAll();
    }
    List<Order> findByProductType(String customerId){
        List<Order> newOrders = List.of();
        for (Order order : orderRepository.orders) {
            if (order.customerId() == customerId) {
                newOrders.add(order);
            }
        }
        return newOrders;
    }
    public long getTotalCustomerAmount(String customerId){
        List<Order> ordersByCustomer = findByProductType(customerId);
        long total = 0;
        for (Order order : ordersByCustomer) {
            total += order.amount();
        }
        return total;
    }

}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
    //ProductService productService = new ProductService();
}

