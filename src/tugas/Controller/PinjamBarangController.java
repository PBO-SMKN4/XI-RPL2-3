/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tugas.JavaMail;
import tugas.help.DBConnect;
import tugas.model.tblPengembalianModel;

/**
 * FXML Controller class
 *
 * @author Fadillah
 */
public class PinjamBarangController implements Initializable {
    
    Connection koneksi = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtJml;
    @FXML
    private ComboBox<String> cmbBarang;
    @FXML
    private Button btnPinjam;
    
    GeneratorQrCode qrCode = new GeneratorQrCode();
    JavaMail javaMail = new JavaMail();
    
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> itemBarang = FXCollections.observableArrayList(
            "Infocus",
            "Terminal"
        );
        
        
        cmbBarang.getItems().removeAll(cmbBarang.getItems());
        cmbBarang.getItems().addAll(itemBarang);
        txtUsername.setText(LoginController.getUsername());
    }    

    @FXML
    private void pinjamBarang(ActionEvent event) throws IOException {
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
                Statement statement = koneksi.createStatement();
            
                String query = "INSERT INTO t_transaksi("
                        + "id_transaksi, id_asset, username, jmlh_pinjam, tgl_pinjam, status) " 
                        +"VALUES('"+ idPinjam +"','"+idBarang +"','"+username+"','"+jmlBarang+"','"+waktu+"','"+statusBarang+"')";

                int status = statement.executeUpdate(query);

                if(status > 0){
                    qrCode.generate(idPinjam);
                    System.out.println("Peminjaman Berhasil");
                    
                    //Kirim Email
                    kirim(idPinjam);
                    JOptionPane.showMessageDialog(null, "Peminjaman Berhasil. Cek Email Sekarang");
                    
                    Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_pengembalianUser.fxml"));
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
    
    void kirim(String idPinjam){
        try{
            Statement stmt = (Statement) koneksi.createStatement();
            String query = "SELECT t_transaksi.* , t_assets.nama_barang, t_login.fullname, t_login.email FROM t_transaksi,t_assets,t_login WHERE t_transaksi.id_transaksi = '" +idPinjam+"' AND t_transaksi.id_asset = t_assets.id_asset AND t_login.username = t_transaksi.username";
            ResultSet rs = stmt.executeQuery(query);
            String pattern = "hh:mm:ss dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            
            int no = 1;
            if(rs.next()){
                
                javaMail.setIdPinjam(rs.getString("id_transaksi"));
                javaMail.setNamaUser(rs.getString("fullname"));
                javaMail.setNamaBarang(rs.getString("nama_barang"));
                javaMail.setJml(rs.getString("jmlh_pinjam"));
                javaMail.setReceiver(rs.getString("email"));
                javaMail.setTglExp("-");
                javaMail.setTglPinjam(sdf.format(rs.getTimestamp("tgl_pinjam")));
                
                javaMail.send();
               
                    
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void btnHome(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtamaUser.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void btnBorrow(MouseEvent event) throws IOException {
    }
    
    @FXML
    private void btnReturn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_pengembalianUser.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void btnReport(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_reportUser.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void btnWarehouse(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_gudangUser.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void exit(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit?");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.out.println("Logout");
            try {
                Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_Login.fxml"));        
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setMaximized(false);
                stage.centerOnScreen();
                
            } catch (IOException ex) {
                Logger.getLogger(HalamanUtamaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void min(ActionEvent event) {
    }

    @FXML
    private void close(ActionEvent event) {
    }

    
}
