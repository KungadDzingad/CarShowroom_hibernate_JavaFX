package com.wojtek.dao;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Connection {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("paom8");

    static EntityManager getManager(){
        return factory.createEntityManager();
    }
}

