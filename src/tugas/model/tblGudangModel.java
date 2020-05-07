/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Roshan
 */
public class tblGudangModel{
    
    private final StringProperty id_asset;
    private final StringProperty nama_barang;
    private final StringProperty brand;
    private final StringProperty category;
    private final IntegerProperty qty;
    private final StringProperty uom;
    private final StringProperty lokasi_barang;
    private final StringProperty jenis;
    private final DoubleProperty price;
    private final DoubleProperty total;
    private final StringProperty kondisi;
    private final ObjectProperty<LocalDate> tanggal_terima;
    private final StringProperty keterangan;
    private final StringProperty foto;
   
//    public tblGudangModel(){
//        this(null,null,null,null,null,null,null,null,null,null,null,null,null,null);
//    }
    
    public tblGudangModel(String id_asset, String nama_barang, String brand,String category, Integer qty, String uom, String lokasi_barang, String jenis, Double price, Double total, String kondisi, LocalDate tanggal_terima, String keterangan, String foto){
        this.id_asset = new SimpleStringProperty(id_asset);
        this.nama_barang = new SimpleStringProperty(nama_barang);
        this.brand = new SimpleStringProperty(brand);
        this.category = new SimpleStringProperty(category);
        this.qty = new SimpleIntegerProperty(qty);
        this.uom = new SimpleStringProperty(uom);
        this.lokasi_barang = new SimpleStringProperty(lokasi_barang);
        this.jenis = new SimpleStringProperty(jenis);
        this.price = new SimpleDoubleProperty(price);
        this.total = new SimpleDoubleProperty(total);
        this.kondisi = new SimpleStringProperty(kondisi);
        this.tanggal_terima = new SimpleObjectProperty<>(tanggal_terima);
        this.keterangan = new SimpleStringProperty(keterangan);
        this.foto = new SimpleStringProperty(foto);            
    }

    public tblGudangModel() {
        this(null, null, null, null, Integer.SIZE, null, null, null, Double.NaN, Double.NaN, null, LocalDate.MIN, null, null);
    }

    public String getIdAsset() {
        return id_asset.get();
    }

    public void setIdAsset(String value) {
        id_asset.set(value);
    }

    public StringProperty idAssetProperty() {
        return id_asset;
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

    public String getBrand() {
        return brand.get();
    }

    public void setBrand(String value) {
        brand.set(value);
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String value) {
        category.set(value);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public int getQty() {
        return qty.get();
    }

    public void setQty(int value) {
        qty.set(value);
    }

    public IntegerProperty qtyProperty() {
        return qty;
    }

    public String getUom() {
        return uom.get();
    }

    public void setUom(String value) {
        uom.set(value);
    }

    public StringProperty uomProperty() {
        return uom;
    }

    public String getLokasi_barang() {
        return lokasi_barang.get();
    }

    public void setLokasi_barang(String value) {
        lokasi_barang.set(value);
    }

    public StringProperty lokasi_barangProperty() {
        return lokasi_barang;
    }

    public String getJenis() {
        return jenis.get();
    }

    public void setJenis(String value) {
        jenis.set(value);
    }

    public StringProperty jenisProperty() {
        return jenis;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double value) {
        price.set(value);
    }

    public DoubleProperty priceProperty() {
        return price;
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

    public String getKondisi() {
        return kondisi.get();
    }

    public void setKondisi(String value) {
        kondisi.set(value);
    }

    public StringProperty kondisiProperty() {
        return kondisi;
    }

    public LocalDate getTanggal_terima() {
        return tanggal_terima.get();
    }

    public void setTanggal_terima(LocalDate value) {
        tanggal_terima.set(value);
    }

    public ObjectProperty<LocalDate> tanggal_terimaProperty() {
        return tanggal_terima;
    }

    public String getKeterangan() {
        return keterangan.get();
    }

    public void setKeterangan(String value) {
        keterangan.set(value);
    }

    public StringProperty keteranganProperty() {
        return keterangan;
    }

    public String getFoto() {
        return foto.get();
    }

    public void setFoto(String value) {
        foto.set(value);
    }

    public StringProperty fotoProperty() {
        return foto;
    }
 
    
    
            
            
    
    
   
     

     
     
}
