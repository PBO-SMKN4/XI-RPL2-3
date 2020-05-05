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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.mail.Session;
import javax.swing.JOptionPane;
import tugas.Main;
import tugas.UserSession;
import tugas.help.DBConnect;


/**
 *
 * @author Roshan
 */
public class LoginController implements Initializable {
    private String nis;
    private String username;
    private String nama;
    private String email;
    private String idKelas;
    
    public Set<String> hash_Set = new HashSet<String>();
    
    static UserSession session;
    static List<String> stringsList;
            
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
    void forget(MouseEvent event) throws IOException{
            Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_forgetpassword.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));    
    }
//   
    @FXML
     void login(MouseEvent event) throws SQLException, IOException, NoSuchAlgorithmException {
         
         String username = tf_username.getText();
         String password = pf_password.getText();
         
         String real_password;
         boolean akunBenar = false;
            
         MessageDigest digest = MessageDigest.getInstance("SHA-1");
         System.out.println(digest);
         digest.reset();
         digest.update(password.getBytes("utf8"));
         real_password = String.format("%040x", new BigInteger(1, digest.digest()));
            
        
        Connection connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT t_login.*, t_data_diri.nis, t_data_diri.id_kelas FROM t_login,`t_data_diri` WHERE t_login.username = t_data_diri.username GROUP BY t_login.username");
        
        while(rs.next() && !akunBenar){
            if((rs.getString("username").equals(username)) && rs.getString("password").equals(real_password)){
                String role = rs.getString("status");
                akunBenar = true;
                
                hash_Set.add(rs.getString("nis"));
                hash_Set.add(rs.getString("fullname"));
                hash_Set.add(rs.getString("email"));
                hash_Set.add(rs.getString("id_kelas"));
                
                session = new UserSession(rs.getString("username"), hash_Set);
                        
                stringsList = new ArrayList<>(session.getPrivileges());
                
                Preferences userPreferences = Preferences.userRoot();
                userPreferences.put("nama","ASD");
                
                
                if(role.equals("admin")){
                    Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtama.fxml"));
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    
                    Screen screen = Screen.getPrimary();
                    Rectangle2D bounds = screen.getVisualBounds();
                    Main.getPrimaryStage().setX(bounds.getMinX());
                    Main.getPrimaryStage().setY(bounds.getMinY());
                    Main.getPrimaryStage().setWidth(bounds.getWidth());
                    Main.getPrimaryStage().setHeight(bounds.getHeight());
                    Main.getPrimaryStage().setMaximized(true);
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    
                    //setFullScreen(true);
                    //setMaximized(boolean)
                    
                    System.out.println("Login Berhasil");
                }
                if(role.equals("user")){
                    
                    
                    
                    //HalamanUtamaUserController user = new HalamanUtamaUserController(rs.getString("nis"), rs.getString("fullname"), rs.getString("username"), rs.getString("email"), rs.getString("id_kelas") );
                    Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtamaUser.fxml"));
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    
                    Screen screen = Screen.getPrimary();
                    Rectangle2D bounds = screen.getVisualBounds();
                    Main.getPrimaryStage().setX(bounds.getMinX());
                    Main.getPrimaryStage().setY(bounds.getMinY());
                    Main.getPrimaryStage().setWidth(bounds.getWidth());
                    Main.getPrimaryStage().setHeight(bounds.getHeight());
                    Main.getPrimaryStage().setMaximized(true);
                    JOptionPane.showMessageDialog(null, "Login Berhasil!");
                    
                }
            }
        }
        
        if(!akunBenar){
            JOptionPane.showMessageDialog(null, "Username/Password Salah!");
        }
    }
     
    public static String getUsername(){
        return session.getUserName();
    }
    public static String getIdKelas(){
        return stringsList.get(0);
    }
    public static String getNama(){
        return stringsList.get(1);
    }
    public static String getNis(){
        return stringsList.get(2);
    }
    public static String getEmail(){
        return stringsList.get(3);
    }
    public void logout(){
        session.cleanUserSession();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tf_username.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                KeyCode kc = ke.getCode();
                if (kc.equals(KeyCode.ENTER)) {
                    //login();
                }
            }
        });
    }    
}
