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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import tugas.model.tblReportModel;
import tugas.model.tblUserModel;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ReportAdminController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
	
    private double xOffset;
    private double yOffset;
    @FXML
    private Circle myCircle;
    @FXML
    private TableColumn col_action;
    @FXML
    private TableColumn<tblReportModel, Integer> col_no;
    @FXML
    private TableColumn<tblReportModel, String> col_nis;
    @FXML
    private TableColumn<tblReportModel, String> col_nama;
    @FXML
    private TableColumn<tblReportModel, String> col_class;
    @FXML
    private TableColumn<tblReportModel, String> col_email;
    @FXML
    private TableColumn<tblReportModel, String> col_report;
    @FXML
    private TextField inpSearch;
    @FXML
    private TableView<tblReportModel> table_report;
    
    private Connection connection;
    private ObservableList<tblReportModel> list;

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
    
    private void populateTableView() {

        try {

            list = FXCollections.observableArrayList();

            String query = "SELECT * from t_login,t_report, t_data_diri, t_kelas where t_login.username = t_data_diri.username AND t_data_diri.id_kelas = t_kelas.id_kelas AND t_data_diri.id_data = t_report.id_data";
            //String query = "SELECT * FROM t_login WHERE status = 'user'";
            connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
            ResultSet rs = connection.createStatement().executeQuery(query);

            int no =1 ;
            while (rs.next()) {

                tblReportModel model = new tblReportModel();
                
                model.setNo(no++);
                model.setId_report(rs.getInt("id_report"));
                model.setNis(rs.getString("nis"));
                model.setNama(rs.getString("fullname"));
                model.setKelas(rs.getString("nama_kelas"));
                model.setEmail(rs.getString("email"));
                model.setIsi_report(rs.getString("isi_report"));

                list.add(model);

            }
            col_no.setCellValueFactory(cellData -> cellData.getValue().NoProperty().asObject());
            col_nis.setCellValueFactory(cellData -> cellData.getValue().nisProperty());
            col_nama.setCellValueFactory(cellData -> cellData.getValue().namaProperty());
            col_class.setCellValueFactory(cellData -> cellData.getValue().kelasProperty());
            col_email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
            col_report.setCellValueFactory(cellData -> cellData.getValue().isi_reportProperty());

            Callback<TableColumn<tblReportModel, String>, TableCell<tblReportModel, String>> cellFactory = (param) -> {
                final TableCell<tblReportModel, String> cell = new TableCell<tblReportModel, String>() {

                    private final Button delete = new Button("Delete");
                    private final Button edit = new Button("Edit");
                    private final HBox pane = new HBox(delete, edit);

                    {
                        delete.setOnAction((ActionEvent event) -> {
                            try {
                                tblReportModel model = getTableView().getItems().get(getIndex());
                                String query = "delete from t_data_diri where username = ?";
                                PreparedStatement pst = connection.prepareStatement(query);
                                pst.setInt(1, model.getId_report());
                                pst.executeUpdate();


                            } catch (SQLException ex) {
                                Logger.getLogger(DataUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });

//                        edit.setOnAction((ActionEvent event) -> {
//                            tblUserModel model = table.getSelectionModel().getSelectedItem();
//                            if (model != null) {
//                                boolean okClicked = Main.showEditDataUser(model);
//                            }
//
//                        });
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        setGraphic(empty ? null : pane);
                    }

                };

                return cell;
            };

            col_action.setCellFactory(cellFactory);

            table_report.setItems(list);

        } catch (SQLException ex) {
            Logger.getLogger(GudangAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FilteredList<tblReportModel> filteredData = new FilteredList<>(list, e -> true);
        inpSearch.setOnKeyReleased(e ->{
            inpSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super tblReportModel>) report ->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter =  newValue.toLowerCase();
                    if(String.valueOf(report.NoProperty()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(report.getNama().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(report.getNis().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(report.getKelas().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(report.getEmail().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(report.getIsi_report().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                
                });
            });
            SortedList<tblReportModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table_report.comparatorProperty());
            table_report.setItems(sortedData);
        
        });

    }
    
    
    
    @FXML
    void btnDashboard(MouseEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtama.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
    
    }
    @FXML
    void btnUser(MouseEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_dataUser.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
    }
    @FXML
    void btnReturn(MouseEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_pengembalian.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
    }
    @FXML
    void btnBorrow(MouseEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_dataPeminjaman.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
    }
    
    @FXML
    void gudang(MouseEvent event) throws IOException{
            Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_gudangAdmin.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
    }
    
    @FXML
    void btnPlanning(MouseEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_planning.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
    }
    
    @FXML
    void exit(MouseEvent event) throws IOException{
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
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
        } else {
               
        }        
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

    
    
}
