/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.model;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Roshan
 */
public class tblPlanningModel {
    
    private final StringProperty id_planning;
    private final StringProperty nama_barang;
    private final IntegerProperty qty;
    private final DoubleProperty price;
    private final StringProperty description;
    private final ObjectProperty<LocalDate> planning_date;
    private final ObjectProperty<LocalDate> date_purchased;
    private final StringProperty status;
    
    public tblPlanningModel(){
        this(null, null, Integer.SIZE, Double.NaN, null, LocalDate.MIN, LocalDate.MIN, null);
    }

    public tblPlanningModel(String id_planning, String nama_barang, Integer qty, Double price, String description,LocalDate planning_date,LocalDate date_purchased, String status) {
        this.id_planning = new SimpleStringProperty(id_planning);
        this.nama_barang = new SimpleStringProperty(nama_barang);
        this.qty = new SimpleIntegerProperty(qty);
        this.price = new SimpleDoubleProperty(price);
        this.description = new SimpleStringProperty(description);
        this.planning_date = new SimpleObjectProperty<LocalDate>(planning_date);
        this.date_purchased = new SimpleObjectProperty<LocalDate>(date_purchased);
        this.status = new SimpleStringProperty(status);
    }

    public final String getId_planning() {
        return id_planning.get();
    }

    public final void setId_planning(String value) {
        id_planning.set(value);
    }

    public StringProperty id_planningProperty() {
        return id_planning;
    }

    public final String getNama_barang() {
        return nama_barang.get();
    }

    public final void setNama_barang(String value) {
        nama_barang.set(value);
    }

    public StringProperty nama_barangProperty() {
        return nama_barang;
    }

    public final int getQty() {
        return qty.get();
    }

    public final void setQty(int value) {
        qty.set(value);
    }

    public IntegerProperty qtyProperty() {
        return qty;
    }

    public final double getPrice() {
        return price.get();
    }

    public final void setPrice(double value) {
        price.set(value);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public final String getDescription() {
        return description.get();
    }

    public final void setDescription(String value) {
        description.set(value);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public final LocalDate getPlanning_date() {
        return planning_date.get();
    }

    public final void setPlanning_date(LocalDate value) {
        planning_date.set(value);
    }

    public ObjectProperty<LocalDate> planning_dateProperty() {
        return planning_date;
    }

    public final LocalDate getDate_purchased() {
        return date_purchased.get();
    }

    public final void setDate_purchased(LocalDate value) {
        date_purchased.set(value);
    }

    public ObjectProperty<LocalDate> date_purchasedProperty() {
        return date_purchased;
    }

    public final String getStatus() {
        return status.get();
    }

    public final void setStatus(String value) {
        status.set(value);
    }

    public StringProperty statusProperty() {
        return status;
    }
    
    
    
   
    
}
