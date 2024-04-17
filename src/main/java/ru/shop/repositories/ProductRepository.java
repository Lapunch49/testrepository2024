package ru.shop.repositories;

import java.util.ArrayList;
import java.util.List;
import ru.shop.entity.Product;

public class ProductRepository implements Repository<Product> {
    List<Product> products = new ArrayList<>();

    public void save(Product product){
        products.add(product);
    }
    public List<Product> findAll(){
        ArrayList<Product> productsCopy = new ArrayList<Product>(products);
        return productsCopy;
    }
}
