package ru.shop.entity;

public record Order(String id, String customerId, String productId, long count, long amount) {
    public Order {
        // проверки
        if (count <=0)
            throw new IllegalArgumentException("count must be greater than 0");
    }
}

