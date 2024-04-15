package ru.shop.Entities;

public record Product(String id, String name, long cost, ProductType productType) {
    public Product{
        // проверки
        if (cost < 0){
            throw new IllegalArgumentException("cost should be greater than 0");
        }
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("name should not be null");
        }

    }
    public String getProductId(){
        return id;
    }
    public ProductType getProductType(){
        return productType;
    }
}
