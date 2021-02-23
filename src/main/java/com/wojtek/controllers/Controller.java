package com.wojtek.controllers;

import com.wojtek.ShowroomModel;
import com.wojtek.WrongRatingException;
import com.wojtek.models.CarShowroom;
import com.wojtek.models.Rating;
import com.wojtek.models.Vehicle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML public TableView<Vehicle> carTable;
    @FXML public TableColumn<Vehicle,String> salonColumn ;
    @FXML public TableColumn<Vehicle,String> miastoColumn ;
    @FXML public TextField markaField;
    @FXML public TextField modelField;
    @FXML public TextField cenaField;
    @FXML public TextField stanField;
    @FXML public TextField przebiegField;
    @FXML public TextField pojemnoscField;
    @FXML public TextField rokField;
    @FXML public ComboBox<CarShowroom> showroomComboBox;
    @FXML public TableView<Rating> ratingTable;
    @FXML public TextField ocenaField;
    @FXML public TextArea opisField;

    private ShowroomModel model;
    private ObservableList<Vehicle> carsObservable = FXCollections.observableArrayList();
    private ObservableList<Rating> ratingObservable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new ShowroomModel();
        for (CarShowroom showroom : model.getShowroomList()) {
            carsObservable.addAll(showroom.getCars());
        }
        initCarTable();
        initComboBox();
        initRatingTable();
    }

    private void initCarTable(){

        carTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("marka"));
        carTable.getColumns().get(1).setCellValueFactory( new PropertyValueFactory<>("model"));
        carTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cena"));
        salonColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCarShowroom().getName()));

        miastoColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getCarShowroom().getMiasto()));

        carTable.setItems(carsObservable);

    }

    private void initComboBox(){
        showroomComboBox.setCellFactory(comboBox -> new ListCell<CarShowroom>() {
            @Override
            protected void updateItem(CarShowroom carShowroom, boolean b) {
                super.updateItem(carShowroom, b);

                if (carShowroom == null || b) {
                    setText(null);
                } else {
                    setText(carShowroom.toString());
                }
            }
        });
        showroomComboBox.getItems().addAll(model.getShowroomList());
        showroomComboBox.getSelectionModel().select(0);
    }


    private void initRatingTable(){
        ratingTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ocena"));
        ratingTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("opis"));
        ratingTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public void addCar(ActionEvent actionEvent) {
        CarShowroom showroom = showroomComboBox.getSelectionModel().getSelectedItem();
        String marka = markaField.getText();
        String modelString = modelField.getText();
        String stan = stanField.getText();
        int cena = Integer.parseInt(cenaField.getText());
        double przebieg = Double.parseDouble(przebiegField.getText());
        double pojemnosc = Double.parseDouble(pojemnoscField.getText());
        int rok = Integer.parseInt(rokField.getText());
        try{
            Vehicle vehicle = new Vehicle(marka,modelString,stan,cena,rok,przebieg,pojemnosc);
            model.addCar(vehicle,showroom);
            carsObservable.add(vehicle);
        }catch (Exception e){
            System.out.println("blad w dodawaniu");
        }
    }

    public void deleteCar(ActionEvent actionEvent) {
        Vehicle vehicle = carTable.getSelectionModel().getSelectedItem();
        if(vehicle != null){
            model.removeCar(vehicle);
            carsObservable.remove(vehicle);
        }
    }


    public void addReview(ActionEvent actionEvent) {
        Vehicle selectedVehicle = carTable.getSelectionModel().getSelectedItem();
        if(selectedVehicle != null) {
            int ocena = Integer.parseInt(ocenaField.getText());
            String opis = opisField.getText();
            java.sql.Date date = new Date(Calendar.getInstance().getTime().getTime());
            Rating rating = new Rating(ocena, opis, date);
            try {
                model.addRating(rating,selectedVehicle);
                ratingObservable.add(rating);
            } catch (WrongRatingException ignored) { }
        }
    }

    public void showVehicleReviews(MouseEvent mouseEvent) {
        Vehicle selectedVehicle = carTable.getSelectionModel().getSelectedItem();
        if(selectedVehicle!= null){
            ratingObservable.setAll(selectedVehicle.getRating());
            ratingTable.setItems(ratingObservable);
        }
    }
}
