/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tugas.Main;
import tugas.help.DBConnect;
import tugas.help.DateUtil;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class TambahPlanningController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Circle myCircle;

    private double xOffset;
    private double yOffset;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_nama_item;
    @FXML
    private TextField tf_qty;
    @FXML
    private TextField tf_price;
    @FXML
    private TextArea ta_desc;
    @FXML
    private DatePicker da_planning_date;
    @FXML
    private DatePicker da_date_purchased;
    
    Connection connection;

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
    public void moveAnchorPane()
    {
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
    void btnDashboard(MouseEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtama.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
    
    }
    
    
    
    
        @FXML
    void btnUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_dataUser.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void btnReturn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_pengembalian.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void btnBorrow(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_dataPeminjaman.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    void gudang(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_gudangAdmin.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void btnReport(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_report.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void btnPlanning(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_planning.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void save(MouseEvent event) throws IOException {
        connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");

        try {
            String id = tf_id.getText();
            String nama_item = tf_nama_item.getText();
            int qty = Integer.parseInt(tf_qty.getText());
            double price = Double.parseDouble(tf_price.getText());
            String date_plan = DateUtil.format(da_planning_date.getValue());
            String date_purch = DateUtil.format(da_date_purchased.getValue());
            String desc = ta_desc.getText();

       

            Statement statement = connection.createStatement();

            String query = "INSERT INTO t_planning(id_planning,nama_barang,qty,price,description,planning_date,date_purchased,status) "
                    + "VALUES('" + id + "','" + nama_item + "','" + qty + "','" + price + "','"+ desc+"','"+ date_plan +"','"+ date_purch +"', 'not yet approved');";
            int status = statement.executeUpdate(query);
           

            if (status > 0) {
                JOptionPane.showMessageDialog(null, "Berhasil Ditambah!");
                Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_planning.fxml"));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
            } else {
                JOptionPane.showMessageDialog(null, "ID sudah terdaftar!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_planning.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
