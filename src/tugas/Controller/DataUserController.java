/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import tugas.Main;
import tugas.help.DBConnect;
import tugas.model.tblGudangModel;
import tugas.model.tblPengembalianModel;
import tugas.model.tblUserModel;

/**
 * FXML Controller class
 *
 * @author Fadillah
 */
public class DataUserController implements Initializable {
    
    private final static int rowsPerPage = 10;
    
    @FXML
    private AnchorPane anchorPane;
    
    private Connection connection;
    private ObservableList<tblUserModel> list;
    private double xOffset;
    private double yOffset;
    @FXML
    private Circle myCircle;
    @FXML
    private TableColumn col_action;
    @FXML
    private TableView<tblUserModel> table;
    @FXML
    private TableColumn<tblUserModel, Integer> col_no;
    @FXML
    private TableColumn<tblUserModel, String> col_username;
    @FXML
    private TableColumn<tblUserModel, String> col_fullname;
    @FXML
    private TableColumn<tblUserModel, String> col_class;
    @FXML
    private TableColumn<tblUserModel, String> col_email;
    @FXML
    private TextField inpSearch;
    @FXML
    private Pagination pagination;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populateTableView();
        this.moveAnchorPane();
        
        myCircle.setStroke(Color.WHITE);
        Image img1 = new Image("/tugas/css/profil.jpg", false);
        myCircle.setFill(new ImagePattern(img1));
        
    }
    
    private void populateTableView() {
        try {
            
            list = FXCollections.observableArrayList();
            
            String query = "SELECT * from t_login, t_data_diri, t_kelas where t_login.username = t_data_diri.username AND t_data_diri.id_kelas = t_kelas.id_kelas";
            //String query = "SELECT * FROM t_login WHERE status = 'user'";
            connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
            ResultSet rs = connection.createStatement().executeQuery(query);
            
            int no = 1;
            while (rs.next()) {
                
                tblUserModel model = new tblUserModel();
                model.setNo(no++);
                model.setNis(rs.getString("nis"));
                model.setUsername(rs.getString("username"));
                model.setFullname(rs.getString("fullname"));
                model.setKelas(rs.getString("nama_kelas"));
                model.setEmail(rs.getString("email"));
                
                list.add(model);
                
            }
            col_no.setCellValueFactory(cellData -> cellData.getValue().noProperty().asObject());
            col_username.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
            col_fullname.setCellValueFactory(cellData -> cellData.getValue().fullnameProperty());
            col_class.setCellValueFactory(cellData -> cellData.getValue().kelasProperty());
            col_email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
            
            Callback<TableColumn<tblUserModel, String>, TableCell<tblUserModel, String>> cellFactory = (param) -> {
                final TableCell<tblUserModel, String> cell = new TableCell<tblUserModel, String>() {
                    
                    private final Button delete = new Button("Delete");
                    private final Button edit = new Button("Edit");
                    private final HBox pane = new HBox(delete, edit);

                    {
                        delete.setOnAction((ActionEvent event) -> {
                            try {
                                tblUserModel model = getTableView().getItems().get(getIndex());
                                String query = "delete from t_data_diri where username = ?";
                                PreparedStatement pst = connection.prepareStatement(query);
                                pst.setString(1, model.getUsername());
                                pst.executeUpdate();
                             
                                String query2 = "delete from t_login where username = ?" ;
                                PreparedStatement pst2 = connection.prepareStatement(query2);
                                pst2.setString(1, model.getUsername());
                                pst2.executeUpdate();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(DataUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        
                        edit.setOnAction((ActionEvent event) -> {
                           tblUserModel model = table.getSelectionModel().getSelectedItem();
                                if (model != null){
                                    boolean okClicked = Main.showEditDataUser(model);   
                                } 
                        
                        });
                    }
                    
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        
                        setGraphic(empty ? null : pane );
                    }
                    
                    
                    
                };
                
                return cell;
            };
            
            col_action.setCellFactory(cellFactory);
            
            table.setItems(list);
            
        } catch (SQLException ex) {
            Logger.getLogger(GudangAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void btnDashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtama.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void btnReturn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_pengembalian.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void btnBorrow(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_dataPeminjaman.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void gudang(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_gudangAdmin.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void btnReport(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_report.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void btnPlanning(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_planning.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    private void btnAddUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_tambahUser.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
    @FXML
    void exit(MouseEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("Logout");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_Login.fxml"));
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setMaximized(false);
                stage.centerOnScreen();
                
            } catch (IOException ex) {
                Logger.getLogger(HalamanUtamaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //action
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
    
}
