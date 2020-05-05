/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Roshan
 */
public class tblReportModel {
    private final IntegerProperty id_report;
    private final IntegerProperty No;
    private final StringProperty nis;
    private final StringProperty nama;
    private final StringProperty kelas;
    private final StringProperty email;
    private final StringProperty isi_report;

    public tblReportModel() {
        this(null,Integer.SIZE, null, null, null, null, null);
    }

    public tblReportModel(Integer id_report,Integer No, String nis, String nama, String kelas, String email, String isi_report) {
       this.id_report = new SimpleIntegerProperty(id_report);
        this.No = new SimpleIntegerProperty(No);
        this.nis = new SimpleStringProperty(nis);
        this.nama = new SimpleStringProperty(nama);
        this.kelas = new SimpleStringProperty(kelas);
        this.email = new SimpleStringProperty(email);
        this.isi_report = new SimpleStringProperty(isi_report);
    }

    public final int getId_report() {
        return id_report.get();
    }

    public final void setId_report(int value) {
        id_report.set(value);
    }

    public IntegerProperty id_reportProperty() {
        return id_report;
    }
    
    

    public final int getNo() {
        return No.get();
    }

    public final void setNo(int value) {
        No.set(value);
    }

    public IntegerProperty NoProperty() {
        return No;
    }

    public final String getNis() {
        return nis.get();
    }

    public final void setNis(String value) {
        nis.set(value);
    }

    public StringProperty nisProperty() {
        return nis;
    }

    public final String getNama() {
        return nama.get();
    }

    public final void setNama(String value) {
        nama.set(value);
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public final String getKelas() {
        return kelas.get();
    }

    public final void setKelas(String value) {
        kelas.set(value);
    }

    public StringProperty kelasProperty() {
        return kelas;
    }

    public final String getEmail() {
        return email.get();
    }

    public final void setEmail(String value) {
        email.set(value);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public final String getIsi_report() {
        return isi_report.get();
    }

    public final void setIsi_report(String value) {
        isi_report.set(value);
    }

    public StringProperty isi_reportProperty() {
        return isi_report;
    }

}
