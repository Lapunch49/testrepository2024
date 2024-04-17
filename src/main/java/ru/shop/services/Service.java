package ru.shop.services;

import java.util.List;

public interface Service<E> {
    public List<E> findAll();
}
