/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tugas.Main;
import tugas.help.DBConnect;
import tugas.help.DateUtil;
import tugas.model.tblGudangModel;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EditBarangController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Circle myCircle;

    private double xOffset;
    private double yOffset;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_namaitem;
    @FXML
    private TextField tf_category;
    @FXML
    private TextField tf_brand;
    @FXML
    private DatePicker dp_date;
    @FXML
    private TextField tf_qty;
    @FXML
    private TextField tf_price;
    @FXML
    private TextField tf_uom;
    @FXML
    private TextField tf_condition;
    @FXML
    private TextField tf_location;
    @FXML
    private TextField tf_foto;
    @FXML
    private ImageView gambaritem;
    @FXML
    private TextField ta_description;
    @FXML
    private TextField ta_tipe;
    
    Connection connection;

    private tblGudangModel gudang;
    private Scene detailStage;
    private boolean okClicked = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.moveAnchorPane();

        myCircle.setStroke(Color.WHITE);
        Image img1 = new Image("/tugas/css/profil.jpg", false);
        myCircle.setFill(new ImagePattern(img1));
    }

    public void moveAnchorPane() {
        anchorPane.setOnMousePressed(event -> {
            xOffset = Main.getPrimaryStage().getX() - event.getScreenX();
            yOffset = Main.getPrimaryStage().getY() - event.getScreenY();
            anchorPane.setCursor(Cursor.CLOSED_HAND);
        });

        anchorPane.setOnMouseDragged(event -> {
            Main.getPrimaryStage().setX(event.getScreenX() + xOffset);
            Main.getPrimaryStage().setY(event.getScreenY() + yOffset);
        });

        anchorPane.setOnMouseReleased(event -> {
            anchorPane.setCursor(Cursor.DEFAULT);
        });
    }

    public void setDetailStage(Scene detailStage) {
        this.detailStage = detailStage;
    }

    public void setDetail(tblGudangModel gudang) {
        this.gudang = gudang;

        tf_id.setText(gudang.getIdAsset());
        tf_namaitem.setText(gudang.getNama_barang());
        tf_brand.setText(gudang.getBrand());
        tf_category.setText(gudang.getCategory());
        ta_description.setText(gudang.getKeterangan());
        tf_uom.setText(gudang.getUom());
        tf_condition.setText(gudang.getKondisi());
        tf_location.setText(gudang.getLokasi_barang());
        ta_tipe.setText(gudang.getJenis());
        tf_price.setText(Double.toString(gudang.getPrice()));
        tf_qty.setText(Integer.toString(gudang.getQty()));
        dp_date.setValue(gudang.getTanggal_terima());
        //   setText(DateUtil.format(gudang.getTanggal_terima()));

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    public void close(ActionEvent event) {
        Main.getPrimaryStage().close();
    }

    @FXML
    public void min(ActionEvent event) {
        Main.getPrimaryStage().setIconified(true);
    }

    @FXML
    private void update(MouseEvent event) {
        try {
            String id = tf_id.getText();
            String nama_item = tf_namaitem.getText();
            String brand = tf_brand.getText();
            String category = tf_category.getText();
            String description = ta_description.getText();
            String uom = tf_uom.getText();
            String condition = tf_condition.getText();
            String location = tf_location.getText();
            String type = ta_tipe.getText();
            String price = tf_price.getText();
            String qty = tf_qty.getText();
            String date = DateUtil.format(dp_date.getValue());
            
            connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
            
            Statement statement = connection.createStatement();
            
            String query = "UPDATE t_assets SET nama_barang = '"+ nama_item +"',"
                    + "brand = '"+ brand +"',"
                    + "qty = '"+ qty +"',"
                    + "uom = '"+ uom +"',"
                    + "lokasi_barang = '"+ location +"',"
                    + "jenis = '"+ type +"',"
                    + "category = '"+ category +"',"
                    + "price = '"+ price +"',"
                    + "kondisi = '"+ condition +"',"
                    + "tanggal_terima = '"+ date +"',"
                    + "keterangan = '"+ description +"'"
                    + " WHERE id_asset = '"+ id +"'";
            System.out.println(query);
            int status = statement.executeUpdate(query);
            if(status == 1 ){
                System.out.println("Edit");
              
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EditProfilUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(MouseEvent event) {
    }

}
