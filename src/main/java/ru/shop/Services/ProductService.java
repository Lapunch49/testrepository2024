package ru.shop.Services;

import java.util.ArrayList;
import java.util.List;

import ru.shop.Entities.Product;
import ru.shop.Entities.ProductType;
import ru.shop.Interfaces.Service;
import ru.shop.Repositories.ProductRepository;

public class ProductService implements Service<Product> {
    ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public void save(Product product){
        productRepository.save(product);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public List<Product> findByProductType(ProductType productType){
        List<Product> products_copy = productRepository.findAll();
        List<Product> productsByProductType =  new ArrayList<Product>();
        for (Product product : products_copy) {
            if (product.getProductType() == productType) {
                productsByProductType.add(product);
            }
        }
        return productsByProductType;
    }
}
