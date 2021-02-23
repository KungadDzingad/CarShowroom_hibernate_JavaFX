package com.wojtek.dao;

import com.wojtek.models.Rating;
import com.wojtek.models.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class VehicleDao extends UniversalDao<Vehicle> {
    @Override
    public List<Vehicle> getAll() {
        List<Vehicle> bookItems = null;

        EntityManager manager = Connection.getManager();
        String query = "SELECT b FROM Vehicle b";
        TypedQuery<Vehicle> tq = manager.createQuery(query, Vehicle.class);
        try{
            bookItems = tq.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookItems;
    }

    @Override
    public void delete(Vehicle vehicle) {
        EntityManager manager = Connection.getManager();
        EntityTransaction transaction = null;
        try{
            transaction = manager.getTransaction();
            transaction.begin();
            Vehicle toDelete = manager.find(Vehicle.class,vehicle.getId());
            manager.remove(toDelete);

            System.out.println(toDelete.getId());
            transaction.commit();

        }catch(Exception e){
            if(transaction!= null)
                transaction.rollback();
            System.out.println("Nie udalo sie usunac");
        }finally {
            manager.close();
        }
    }
}
