package com.wojtek.models;

import net.bytebuddy.build.Plugin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="car_showroom")
public class CarShowroom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="car_showroom_id",unique = true, nullable = false)
    private long id;

    @Column(name="nazwa", nullable = false)
    private String name;

    @Column(name="miasto", nullable = false)
    private String miasto;

    @Column(name="pojemnosc", nullable = false)
    private int maxCapacity;

    @OneToMany(mappedBy = "carShowroom",
        fetch = FetchType.EAGER
    )
    private List<Vehicle> cars = new ArrayList<>();


    public boolean addCar(Vehicle vehicle){
        if( cars.size() < maxCapacity) {
            cars.add(vehicle);
            vehicle.setCarShowroom(this);
            return true;
        }
        return false;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<Vehicle> getCars() {
        return cars;
    }

    public void setCars(List<Vehicle> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return name;
    }
}
