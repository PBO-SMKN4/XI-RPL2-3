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
 * FXML Controller class
 *
 * @author Roshan
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_username;
    
    @FXML
    private TextField tf_email;
    
    @FXML
    private PasswordField pf_password;
    
    @FXML
    void login(MouseEvent event) throws IOException{
             Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_Login.fxml"));
            
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.setScene(new Scene(root));
    }
    
    @FXML
    void sign_up(MouseEvent event) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        
        Connection connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "idea_drop");
        
        try {
            String fullname = tf_name.getText();
            String username = tf_username.getText();
            String email = tf_email.getText();
            String password = pf_password.getText();
            String real_password;
            
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes("utf8"));
            real_password = String.format("%040x", new BigInteger(1, digest.digest()));
            
            
            Statement statement = connection.createStatement();
            
            String query = "INSERT INTO login(fullname,username,email,password) " 
                    +"VALUES('"+fullname+"','"+username+"','"+email+"','"+real_password+"')";
            
            int status = statement.executeUpdate(query);
            
            if(status > 0){
                System.out.println("User Teregistrasi");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
