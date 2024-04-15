package ru.shop.Interfaces;

import ru.shop.Entities.Customer;

import java.util.List;

public interface Service<E> {
    public List<E> findAll();
}
