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
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tugas.help.DBConnect;

/**
 *
 * @author Roshan
 */
public class LoginController implements Initializable {
    
    
    @FXML 
    private TextField tf_username;
    
    @FXML
    private PasswordField pf_password;
    
    @FXML
    void sign_up(MouseEvent event) throws IOException{
            Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_register.fxml"));
            
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.setScene(new Scene(root));
    }
   
    
    
    @FXML
     void login(MouseEvent event) throws SQLException, IOException, NoSuchAlgorithmException {
         
         String username = tf_username.getText();
         String password = pf_password.getText();
         
         String real_password;
            
         MessageDigest digest = MessageDigest.getInstance("SHA-1");
         digest.reset();
         digest.update(password.getBytes("utf8"));
         real_password = String.format("%040x", new BigInteger(1, digest.digest()));
            
         
        Connection connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "idea_drop");
        
        Statement statement = connection.createStatement();
//        String query = "SELECT * FROM login where username" + " = '" +username+"' or email" +
//                pf_password+ "' and password = '" +real_password+ "')";
        ResultSet resultSet = statement.executeQuery("select * from login where username" +
                " = '" + username + "' or email = '" + pf_password + "' and password = '" + real_password + "'");
        
        
        if(resultSet.next()){
            Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtama.fxml"));
            
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.setScene(new Scene(root));
        }
        else{
            System.out.println("Salah");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
