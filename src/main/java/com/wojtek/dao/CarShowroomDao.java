package com.wojtek.dao;

import com.wojtek.models.CarShowroom;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarShowroomDao extends UniversalDao<CarShowroom> {


    @Override
    public List<CarShowroom> getAll() {
        List<CarShowroom> bookItems = null;

        EntityManager manager = Connection.getManager();
        String query = "SELECT b FROM CarShowroom b";
        TypedQuery<CarShowroom> tq = manager.createQuery(query, CarShowroom.class);
        try{
            bookItems = tq.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookItems;
    }

    @Override
    public void delete(CarShowroom carShowroom) {

    }
}
