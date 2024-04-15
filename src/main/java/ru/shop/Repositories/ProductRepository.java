package ru.shop.Repositories;

import java.util.ArrayList;
import java.util.List;
import ru.shop.Entities.Product;
import ru.shop.Interfaces.Repository;

public class ProductRepository implements Repository<Product> {
    List<Product> products = new ArrayList<>();

    public void save(Product product){
        products.add(product);
    }
    public List<Product> findAll(){
        ArrayList<Product> products_copy = new ArrayList<Product>(products);
        return products_copy;
    }
}
