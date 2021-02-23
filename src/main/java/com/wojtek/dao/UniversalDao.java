package com.wojtek.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class UniversalDao<T> implements Dao<T> {
    @Override
    public void save(T t){
        EntityManager manager = Connection.getManager();
        EntityTransaction transaction = null;
        try{
            transaction = manager.getTransaction();
            transaction.begin();

            manager.persist(t);

            transaction.commit();

        } catch (Exception e) {
            if(transaction!=null)transaction.rollback();
            e.printStackTrace();
        }
    }


}
