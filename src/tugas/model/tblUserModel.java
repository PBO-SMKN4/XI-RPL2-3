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
 * @author Fadillah
 */
public class tblUserModel {

    private final StringProperty nis;
    private final StringProperty username;
    private final StringProperty fullname;
    private final StringProperty kelas;
    private final StringProperty email;

    public tblUserModel( String nis, String username, String fullname, String kelas, String email) {

        this.nis = new SimpleStringProperty(nis);
        this.username = new SimpleStringProperty(username);
        this.fullname = new SimpleStringProperty(fullname);
        this.kelas = new SimpleStringProperty(kelas);
        this.email = new SimpleStringProperty(email);

    }

    public tblUserModel() {
        this( null, null, null, null, null);
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

    public final String getUsername() {
        return username.get();
    }

    public final void setUsername(String value) {
        username.set(value);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public final String getFullname() {
        return fullname.get();
    }

    public final void setFullname(String value) {
        fullname.set(value);
    }

    public StringProperty fullnameProperty() {
        return fullname;
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

}
