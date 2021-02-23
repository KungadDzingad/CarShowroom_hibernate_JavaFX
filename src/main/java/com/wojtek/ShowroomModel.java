package com.wojtek;

import com.wojtek.dao.CarShowroomDao;
import com.wojtek.dao.RatingDao;
import com.wojtek.dao.VehicleDao;
import com.wojtek.models.CarShowroom;
import com.wojtek.models.Rating;
import com.wojtek.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ShowroomModel {
    private List<CarShowroom> showroomList;

    public ShowroomModel(){
        showroomList = new CarShowroomDao().getAll();
    }


    public void addShowroom(CarShowroom carShowroom){
        showroomList.add(carShowroom);
        carShowroom.setCars(new ArrayList<>());
        new CarShowroomDao().save(carShowroom);
    }


    public void addCar(Vehicle car, CarShowroom selectedShowroom){
        boolean a = selectedShowroom.addCar(car);
        if(a)
            new VehicleDao().save(car);
    }

    public List<CarShowroom> getShowroomList() {
        return showroomList;
    }

    public void removeCar(Vehicle vehicle) {
        vehicle.getCarShowroom().getCars().remove(vehicle);
        new VehicleDao().delete(vehicle);
    }

    public void addRating(Rating rating, Vehicle vehicle) throws WrongRatingException {
        if(rating.getOcena()>5 || rating.getOcena() < 1)
            throw new WrongRatingException();

        vehicle.addRating(rating);
        new RatingDao().save(rating);
    }
}
