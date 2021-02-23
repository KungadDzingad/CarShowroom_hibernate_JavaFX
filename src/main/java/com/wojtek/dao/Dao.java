package com.wojtek.dao;

import java.util.List;

interface Dao<T> {
    void save(T t);
    List<T> getAll();
    void delete(T t);
}
