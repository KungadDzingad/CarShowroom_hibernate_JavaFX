package com.wojtek.dao;

import com.wojtek.models.CarShowroom;
import com.wojtek.models.Rating;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RatingDao extends UniversalDao<Rating> {
    @Override
    public List<Rating> getAll() {
        List<Rating> bookItems = null;

        EntityManager manager = Connection.getManager();
        String query = "SELECT b FROM Rating b";
        TypedQuery<Rating> tq = manager.createQuery(query, Rating.class);
        try{
            bookItems = tq.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookItems;
    }

    @Override
    public void delete(Rating rating) {

    }
}
