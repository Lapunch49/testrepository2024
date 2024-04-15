package ru.shop.Interfaces;

import ru.shop.Entities.Customer;

import java.util.List;


public interface Repository<E> {
    public void save(E entity);
    public List<E> findAll();
}
