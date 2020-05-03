/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import tugas.help.DateUtil;
import tugas.model.tblUserModel;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EditProfilUserController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Circle myCircle;
    
    private double xOffset;
    private double yOffset;
    @FXML
    private TextField tf_nis;
    @FXML
    private TextField tf_kelas;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_fullname;

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
        tf_kelas.setText(user.getKelas());
        
    }
    
    public boolean isOkClicked() {
        return okClicked;
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
        String nis = tf_nis.getText();
        String fullname = tf_fullname.getText();
        String username = tf_username.getText();
        String email = tf_email.getText();
        String kelas = tf_kelas.getText();
        
//        Statement statement = connection.createStatement();
//        
//        String query = "UPDATE t_data_diri SET username = '"+ real_password +"' WHERE username = '"+ username +"'";
//
//            int status = statement.executeUpdate(query);
    }

    @FXML
    private void back(MouseEvent event) {
    }
    
}
