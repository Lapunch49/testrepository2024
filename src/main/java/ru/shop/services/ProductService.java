package ru.shop.services;

import java.util.ArrayList;
import java.util.List;

import ru.shop.entity.Product;
import ru.shop.entity.ProductType;
import ru.shop.repositories.ProductRepository;

public class ProductService implements Service<Product> {
    private final ProductRepository productRepository;
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
        List<Product> productsСopy = productRepository.findAll();
        List<Product> productsByProductType =  new ArrayList<Product>();
        for (Product product : productsСopy) {
            if (product.getProductType() == productType) {
                productsByProductType.add(product);
            }
        }
        return productsByProductType;
    }
}
