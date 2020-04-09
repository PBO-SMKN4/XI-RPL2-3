/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import tugas.Main;
import tugas.help.DBConnect;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class TambahController implements Initializable {

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
    private TextArea ta_description;
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

    private FileChooser filechooser;
    private File file;

    Connection connection;
    private Window primaryStage;
    @FXML
    private ImageView gambaritem;

    private Image image;
    private FileInputStream fis;
    @FXML
    private TextField tf_foto;

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

    @FXML
    public void close(ActionEvent event) {

        Main.getPrimaryStage().close();

    }

    @FXML
    public void min(ActionEvent event) {

        Main.getPrimaryStage().setIconified(true);
    }

    @FXML
    void save(MouseEvent event) throws FileNotFoundException {
        connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
        try {
            String id = tf_id.getText();
            String nama_item = tf_namaitem.getText();
            String category = tf_category.getText();
            String brand = tf_brand.getText();
            String description = ta_description.getText();
            String date = dp_date.getEditor().getText();
            int qty = Integer.parseInt(tf_qty.getText());
            double price = Integer.parseInt(tf_price.getText());
            String uom = tf_uom.getText();
            String condition = tf_condition.getText();
            String location = tf_location.getText();
            double total = price * qty;
            String foto = tf_foto.getText();
            foto = foto.replace("\\", "\\\\");

            Statement statement = connection.createStatement();

            String query = "INSERT INTO t_assets(id,nama_barang,brand,category,qty,uom,lokasi_barang,price,total,kondisi,tanggal_terima,keterangan,foto) "
                    + "VALUES('" + id + "','" + nama_item + "','" + brand + "','" + category + "','" + qty + "','" + uom + "','" + location + "','" + price + "','" + total + "','" + condition + "','" + date + "','" + description + "','" + foto + "')";

            System.out.println(query);
            int status = statement.executeUpdate(query);

            if (status > 0) {
                System.out.println("Item Terdaftar");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TambahController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void back(MouseEvent event) {
    }

    @FXML
    void image(MouseEvent event) {
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        file = filechooser.showOpenDialog(primaryStage);

        if (file != null) {
            tf_foto.setText(file.getAbsolutePath());
            image = new Image(file.toURI().toString());
            gambaritem.setImage(image);

        }
    }

}
