/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tugas.Main;
import tugas.help.DBConnect;
import tugas.help.DateUtil;
import tugas.model.tblUserModel;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EditProfilUserController implements Initializable {

    
    Connection connection;
    
    private final ObservableList<String> list = FXCollections.observableArrayList();
    
    @FXML
    private AnchorPane anchorPane;
    
    private Circle myCircle;
    
    private double xOffset;
    private double yOffset;
    @FXML
    private TextField tf_nis;
   
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_fullname;
    @FXML
    private JFXComboBox<String> cmb_kelas;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.moveAnchorPane();
        fillComboBox();
    }
    
    private Stage detailStage;
    private tblUserModel user;
    private boolean okClicked = false;
    
    public void setDetailStage(Stage detailStage) {
        this.detailStage = detailStage;
    }
    
    public void setEdit(tblUserModel user) {
        this.user = user;
        
        tf_nis.setText(user.getNis());
        tf_fullname.setText(user.getFullname());
        tf_username.setText(user.getUsername());
        tf_email.setText(user.getEmail());
        cmb_kelas.setValue(user.getKelas());
      
        
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    public void fillComboBox() {
        try {
            connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
            String query = "SELECT * from t_kelas";

            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("id_kelas"));    
            }
             cmb_kelas.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(TambahUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void save(MouseEvent event) {
        try {
            String nis = tf_nis.getText();
            String fullname = tf_fullname.getText();
            String username = tf_username.getText();
            String email = tf_email.getText();
            String kelas = cmb_kelas.getValue();
            
            
            
            Statement statement = connection.createStatement();
            
            String query = "UPDATE t_data_diri SET id_kelas = '"+ kelas +"' WHERE username = '"+ username +"'";
            String query2 = "UPDATE t_login SET fullname = '"+ fullname +"',email = '"+ email +"' WHERE username = '"+ username +"'";
            System.out.println(query);
            int status2 = statement.executeUpdate(query2);
            int status = statement.executeUpdate(query);
            if(status == 1 && status2 == 1){
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
