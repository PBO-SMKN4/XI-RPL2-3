/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import tugas.help.DBConnect;
import tugas.model.tblPengembalianModel;

/**
 * FXML Controller class
 *
 * @author Fadillah
 */
public class PengembalianController implements Initializable {
    boolean tgglIsTrue = false;
    
    Connection koneksi = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");

    @FXML
    private TableView<tblPengembalianModel> table;
    ObservableList<tblPengembalianModel> oblist = FXCollections.observableArrayList();
    
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblStatus;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Circle myCircle;
    @FXML
    private TextField inpId;
    @FXML
    private Button cariID;
    @FXML
    private AnchorPane formData;
    @FXML
    private TableColumn<tblPengembalianModel, String> colNo;
    @FXML
    private TableColumn<tblPengembalianModel, String> colNameItem;
    @FXML
    private TableColumn<tblPengembalianModel, String> colCategory;
    @FXML
    private TableColumn<tblPengembalianModel, String> colQty;
    @FXML
    private Label lblId;
    @FXML
    private Label lblDateBorrow;
    @FXML
    private Label lblDateReturn;
    @FXML
    private Button btnKembalikan;
    @FXML
    private JFXToggleButton tgglScan;
    @FXML
    private Label lblSearchId;
    @FXML
    private FontAwesomeIconView iconSearch;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formData.setVisible(false);
        
        tgglScan.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue == true) {
                inpId.setText("");
                tgglIsTrue = true;
                cariID.setVisible(false);
                cariID.setOpacity(0);
                lblSearchId.setText("Scan ID");
                inpId.setPromptText("Scan Here");
            }
                else{
                tgglIsTrue = false;
                cariID.setVisible(true);
                cariID.setOpacity(1);
                lblSearchId.setText("Search ID");
                inpId.setPromptText("Search");
                inpId.setText("");
            }
        }));
        
        inpId.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(tgglIsTrue){
                    cariID();
                    inpId.setText("");
                }
            }
        });
        
        
        inpId.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                KeyCode kc = ke.getCode();
                if (kc.equals(KeyCode.ENTER)) {
                    cariID();
                }
            }
        });
        
        
   }    

    @FXML
    private void cariID() {
        try{
            Statement stmt = (Statement) koneksi.createStatement();
            String query = "SELECT t_transaksi.id_transaksi, t_transaksi.username, t_transaksi.status, t_transaksi.jmlh_pinjam, t_assets.nama_barang, t_assets.kategori FROM t_transaksi,t_assets WHERE t_transaksi.id_transaksi = '" + inpId.getText() +"' AND t_assets.id_assets = t_transaksi.id_assets";
            ResultSet rs = stmt.executeQuery(query);
            
            int no = 1;
            if(rs.next()){
                table.getItems().clear();
                oblist.add(new tblPengembalianModel(rs.getString("nama_barang"),rs.getString("kategori"), rs.getString("jmlh_pinjam") ));
                inpId.setText("");
                lblId.setText(rs.getString("id_transaksi"));
                lblUsername.setText(rs.getString("username"));
                lblStatus.setText(rs.getString("status"));
                no++;
                
                if(rs.getString("status").equals("Selesai")){
                    btnKembalikan.setDisable(true);
                    btnKembalikan.setText("Selesai");
                }else{
                    btnKembalikan.setVisible(true);
                }
                    
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        
        colNameItem.setCellValueFactory(new PropertyValueFactory<>("nameItem"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        
        
        table.setItems(oblist);
        
        formData.setVisible(true);
    }

    @FXML
    private void kembalikanBarang(ActionEvent event) {
        try{
            Statement stmt = (Statement) koneksi.createStatement();
            String query = "UPDATE t_transaksi SET status = 'Selesai'"+", tgl_kembali = '"+LocalDateTime.now()+"' WHERE id_transaksi = '" + lblId.getText()+"';";  
            stmt.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void min(ActionEvent event) {
    }

    @FXML
    private void close(ActionEvent event) {
    }   
}
