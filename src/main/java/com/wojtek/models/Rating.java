package com.wojtek.models;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id",unique = true, nullable = false)
    private long id;

    @Column(name="ocena",nullable = false)
    private int ocena;

    @Column(name="opis",nullable = true)
    private String opis;

    @Column(name="data", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="car_id")
    private Vehicle vehicle;

    public Rating(){ }

    public Rating(int ocena, String opis, Date date) {
        this.ocena = ocena;
        this.opis = opis;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
