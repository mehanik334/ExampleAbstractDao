package com.denisenko.dao;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDao<T, ID> implements GenericDao<T, ID> {
    protected final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public T get(ID id) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public void delete(ID t) {

    }

    @Override
    public List<T> getAll() {
        return null;
    }
}


