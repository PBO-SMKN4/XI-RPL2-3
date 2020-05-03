/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.PasswordField;
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

/**
 * FXML Controller class
 *
 * @author asus
 */
public class TambahUserController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
	
    private double xOffset;
    private double yOffset;
    @FXML
    private Circle myCircle;
    @FXML
    private TextField inpFullname;
    @FXML
    private TextField inpUsername;
    @FXML
    private PasswordField inpPassword;
    @FXML
    private TextField inpNis;
    @FXML
    private TextField inpEmail;

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
    public void moveAnchorPane(){
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
    private void btnSave(MouseEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
        Connection connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
        
        try {
            String nis = inpNis.getText();
            String fullname = inpFullname.getText();
            String username = inpUsername.getText();
            String email = inpEmail.getText();
            String password = inpPassword.getText();
            String real_password;
            
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes("utf8"));
            real_password = String.format("%040x", new BigInteger(1, digest.digest()));
            
            
            Statement statement = connection.createStatement();
            
            String query = "INSERT INTO t_login(fullname,username,email,password,status) " 
                    +"VALUES('"+fullname+"','"+username+"','"+email+"','"+real_password+"', 'user');";
            String query2 = "INSERT INTO t_data_diri(username, id_kelas, nis) VALUES('"+username+"', '1','"+nis+"');";
            int status = statement.executeUpdate(query);
            statement.executeUpdate(query2);
            
            if(status > 0){
                JOptionPane.showMessageDialog(null, "Berhasil Ditambah!");
                Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_dataUser.fxml"));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
            }else{
                JOptionPane.showMessageDialog(null, "Username sudah terdaftar!");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void btnBack(MouseEvent event) throws IOException {
        Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_dataUser.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
}
