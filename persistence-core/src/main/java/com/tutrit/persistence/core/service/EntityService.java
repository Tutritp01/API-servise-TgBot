package com.tutrit.persistence.core.service;

public interface EntityService<T,E> {
    T save(T obj);
    T getEntity(E obj);
}
