/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.model;

import java.time.LocalDate;

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
 * @author Fadillah
 */
public class tblGudangModel {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty brand = new SimpleStringProperty();
    private final StringProperty category = new SimpleStringProperty();
    private final IntegerProperty qty = new SimpleIntegerProperty();
    private final StringProperty uom = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final StringProperty lokasi_barang = new SimpleStringProperty();
    private final StringProperty kondisi = new SimpleStringProperty();
    private final StringProperty note = new SimpleStringProperty();
    private final StringProperty foto = new SimpleStringProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();
    private final StringProperty nama_barang = new SimpleStringProperty();
    private final StringProperty tanggal_terima = new SimpleStringProperty();

    public String getTanggal_terima() {
        return tanggal_terima.get();
    }

    public void setTanggal_terima(String value) {
        tanggal_terima.set(value);
    }

    public StringProperty tanggal_terimaProperty() {
        return tanggal_terima;
    }
    

    public String getNama_barang() {
        return nama_barang.get();
    }

    public void setNama_barang(String value) {
        nama_barang.set(value);
    }

    public StringProperty nama_barangProperty() {
        return nama_barang;
    }
    
    
    public double getTotal() {
        return total.get();
    }

    public void setTotal(double value) {
        total.set(value);
    }

    public DoubleProperty totalProperty() {
        return total;
    }
    
    public final String getId() {
        return id.get();
    }

    public final void setId(String value) {
        id.set(value);
    }

    public StringProperty idProperty() {
        return id;
    }

    public final String getBrand() {
        return brand.get();
    }

    public final void setBrand(String value) {
        brand.set(value);
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public final String getCategory() {
        return category.get();
    }

    public final void setCategory(String value) {
        category.set(value);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public final int getQty() {
        return qty.get();
    }

    public final void setQty(Integer value) {
        qty.set(value);
    }

    public IntegerProperty qtyProperty() {
        return qty;
    }

    public final String getUom() {
        return uom.get();
    }

    public final void setUom(String value) {
        uom.set(value);
    }

    public StringProperty uomProperty() {
        return uom;
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

    public final String getLokasi_barang() {
        return lokasi_barang.get();
    }

    public final void setLokasi_barang(String value) {
        lokasi_barang.set(value);
    }

    public StringProperty lokasi_barangProperty() {
        return lokasi_barang;
    }


    public final String getKondisi() {
        return kondisi.get();
    }

    public final void setKondisi(String value) {
        kondisi.set(value);
    }

    public StringProperty kondisiProperty() {
        return kondisi;
    }

   

    public final String getNote() {
        return note.get();
    }

    public final void setNote(String value) {
        note.set(value);
    }

    public StringProperty noteProperty() {
        return note;
    }

    public final String getFoto() {
        return foto.get();
    }

    public final void setFoto(String value) {
        foto.set(value);
    }

    public StringProperty fotoProperty() {
        return foto;
    }

    
    
    
            
}