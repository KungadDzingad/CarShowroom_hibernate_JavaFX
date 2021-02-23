package com.wojtek.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="car_id", unique = true, nullable = false)
    private long id;

    @Column(name="marka", nullable = false)
    private String marka;

    @Column(name="model", nullable = false)
    private String model;

    @Column(name = "stan", nullable = false)
    private String stan;

    @Column(name="cena", nullable = false)
    private double cena;

    @Column(name = "rok_produkcji", nullable = false)
    private int rokProdukcji;

    @Column(name="przebieg", nullable = false)
    private double przebieg;

    @Column(name = "pojemnosc_silnika")
    private double pojemnoscSilnika;


    @ManyToOne
    @JoinColumn(name = "car_showroom_id")
    private CarShowroom carShowroom;

    @OneToMany(mappedBy = "vehicle",cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    public Vehicle(){}

    public Vehicle(String marka, String model, String stan, double cena, int rokProdukcji, double przebieg, double pojemnoscSilnika) {
        this.marka = marka;
        this.model = model;
        this.stan = stan;
        this.cena = cena;
        this.rokProdukcji = rokProdukcji;
        this.przebieg = przebieg;
        this.pojemnoscSilnika = pojemnoscSilnika;
        //ratings = new ArrayList<>();
    }

    public void addRating(Rating rating){
        ratings.add(rating);
        rating.setVehicle(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarShowroom getCarShowroom() {
        return carShowroom;
    }

    public void setCarShowroom(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    public List<Rating> getRating() {
        return ratings;
    }

    public void setRating(List<Rating> rating) {
        this.ratings = rating;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getRokProdukcji() {
        return rokProdukcji;
    }

    public void setRokProdukcji(int rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public double getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(double przebieg) {
        this.przebieg = przebieg;
    }

    public double getPojemnoscSilnika() {
        return pojemnoscSilnika;
    }

    public void setPojemnoscSilnika(double pojemnoscSilnika) {
        this.pojemnoscSilnika = pojemnoscSilnika;
    }
}
