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
 * @author asus
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField tf_username;
    
    @FXML
    private PasswordField pf_password;
    
    @FXML
    private PasswordField pf_verify;
    
    @FXML
    void login(MouseEvent event) throws IOException{
         Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_Login.fxml"));
            
         Node node = (Node) event.getSource();
            
         Stage stage = (Stage) node.getScene().getWindow();
            
         stage.setScene(new Scene(root));
    }
    
    @FXML 
    void change(MouseEvent event) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException{
        String username = tf_username.getText();
        String password = pf_password.getText();
        String verify = pf_verify.getText();
        String real_password;
            
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(password.getBytes("utf8"));
        real_password = String.format("%040x", new BigInteger(1, digest.digest()));
        
        Connection connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
        
        Statement statement = connection.createStatement();
        
        String query = "UPDATE t_login SET password = '"+ real_password +"' WHERE username = '"+ username +"'";
        
        if(password.equals(verify)){
            statement.executeUpdate(query);
            System.out.println("Password Diubah");
        }
        else{
            System.out.println("---");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
