/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tugas.help.DBConnect;

/**
 * FXML Controller class
 *
 * @author Fadillah
 */
public class PinjamBarangController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtJml;
    @FXML
    private ComboBox<String> cmbBarang;
    @FXML
    private Button btnPinjam;
    
    GeneratorQrCode qrCode = new GeneratorQrCode();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> itemBarang = FXCollections.observableArrayList(
            "Infocus",
            "Terminal",
            "DLL"
        );
        
        
        cmbBarang.getItems().removeAll(cmbBarang.getItems());
        cmbBarang.getItems().addAll(itemBarang);
        txtUsername.setText(LoginController.getUsername());
    }    

    @FXML
    private void pinjamBarang(ActionEvent event) throws IOException {
        Connection connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
        
        try {
            String username = LoginController.getUsername();
            String barang = cmbBarang.getSelectionModel().getSelectedItem(); 
            String jmlBarang = txtJml.getText();
            LocalDateTime waktu = LocalDateTime.now();
            String statusBarang = "pinjam";
            String idBarang = "";
            String idPinjam = UUID.randomUUID().toString();
            idPinjam = idPinjam.substring(0, 8);
            
            if(barang != null && jmlBarang != null ){
                if(barang.equals("Infocus")){
                    idBarang = "ID01";
                }
                Statement statement = connection.createStatement();
            
                String query = "INSERT INTO t_transaksi("
                        + "id_transaksi, id_asset, username, jmlh_pinjam, tgl_pinjam, status) " 
                        +"VALUES('"+ idPinjam +"','"+idBarang +"','"+username+"','"+jmlBarang+"','"+waktu+"','"+statusBarang+"')";

                int status = statement.executeUpdate(query);

                if(status > 0){
                    qrCode.generate(idPinjam);
                    System.out.println("Peminjaman Berhasil");
                    JOptionPane.showMessageDialog(null, "Peminjaman Berhasil");
                    Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtamaUser.fxml"));
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setScene(new Scene(root));
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Input Data Dengan Benar!!!");
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}