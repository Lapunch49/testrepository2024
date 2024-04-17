package ru.shop.repositories;

import java.util.List;


public interface Repository<E> {
    public void save(E entity);
    public List<E> findAll();
}
