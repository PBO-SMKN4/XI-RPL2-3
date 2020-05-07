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
import java.time.LocalDate;
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
import javafx.scene.control.Button;
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
import tugas.help.DateUtil;
import tugas.model.tblPlanningModel;
import tugas.model.tblReportModel;
import tugas.model.tblUserModel;

/**
 * FXML Controller class
 *
 * @author Fadillah
 */
public class PlanningController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Circle myCircle;
    
     private Connection connection;
    private ObservableList<tblPlanningModel> list;

    private double xOffset;
    private double yOffset;
    @FXML
    private TableView<tblPlanningModel> table_planning;
    @FXML
    private TableColumn<tblPlanningModel, String> col_id;
    @FXML
    private TableColumn<tblPlanningModel, String> col_namaitem;
    @FXML
    private TableColumn<tblPlanningModel, Integer> col_qty;
    @FXML
    private TableColumn<tblPlanningModel, Double> col_price;
    @FXML
    private TableColumn<tblPlanningModel, String> col_desc;
    @FXML
    private TableColumn<tblPlanningModel, LocalDate> col_planning_date;
    @FXML
    private TableColumn<tblPlanningModel, LocalDate> col_date_purchased;
    @FXML
    private TableColumn<tblPlanningModel, String> col_status;
    @FXML
    private TableColumn col_action;
    @FXML
    private TextField inpsearch;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         this.moveAnchorPane();
         populateTableView();
         
        myCircle.setStroke(Color.WHITE);
        Image img1 = new Image("/tugas/css/profil.jpg", false);
        myCircle.setFill(new ImagePattern(img1));
    }
 public void moveAnchorPane()
    {
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
    
    private void populateTableView() {

        try {

            list = FXCollections.observableArrayList();

            String query = "SELECT * from t_Planning";
            //String query = "SELECT * FROM t_login WHERE status = 'user'";
            connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
            ResultSet rs = connection.createStatement().executeQuery(query);

     
            while (rs.next()) {

                tblPlanningModel model = new tblPlanningModel();
                
                model.setId_planning(rs.getString("id_planning"));
                model.setNama_barang(rs.getString("nama_barang"));
                model.setQty(rs.getInt("qty"));
                model.setPrice(rs.getDouble("price"));
                model.setDescription(rs.getString("description"));
                model.setPlanning_date(DateUtil.parse(rs.getString("planning_date")));
                model.setDate_purchased(DateUtil.parse(rs.getString("date_purchased")));
                model.setStatus(rs.getString("status"));

                list.add(model);

            }
            col_id.setCellValueFactory(celldata -> celldata.getValue().id_planningProperty());
            col_namaitem.setCellValueFactory(celldata -> celldata.getValue().nama_barangProperty());
            col_qty.setCellValueFactory(celldata -> celldata.getValue().qtyProperty().asObject());
            col_price.setCellValueFactory(celldata -> celldata.getValue().priceProperty().asObject());
            col_desc.setCellValueFactory(celldata -> celldata.getValue().descriptionProperty());
            col_planning_date.setCellValueFactory(celldata -> celldata.getValue().planning_dateProperty());
            col_date_purchased.setCellValueFactory(celldata -> celldata.getValue().date_purchasedProperty());
            col_status.setCellValueFactory(celldata -> celldata.getValue().statusProperty());

            Callback<TableColumn<tblPlanningModel, String>, TableCell<tblPlanningModel, String>> cellFactory = (param) -> {
                final TableCell<tblPlanningModel, String> cell = new TableCell<tblPlanningModel, String>() {

                    private final Button delete = new Button("Delete");
                    private final Button edit = new Button("Edit");
                    private final HBox pane = new HBox(delete, edit);

                    {
                        delete.setOnAction((ActionEvent event) -> {
                            try {
                                tblPlanningModel model = getTableView().getItems().get(getIndex());
                                String query = "delete from t_planning where username = ?";
                                PreparedStatement pst = connection.prepareStatement(query);
                                pst.setString(1, model.getId_planning());
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

            table_planning.setItems(list);
            
            

        } catch (SQLException ex) {
            Logger.getLogger(GudangAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FilteredList<tblPlanningModel> filteredData = new FilteredList<>(list, e -> true);
        inpsearch.setOnKeyReleased(e ->{
            inpsearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super tblPlanningModel>) planning ->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter =  newValue.toLowerCase();
                    if(planning.getId_planning().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(planning.getNama_barang().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(String.valueOf( planning.getQty()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(String.valueOf(planning.getPrice()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(planning.getDescription().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(planning.getPlanning_date().toString().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(planning.getDate_purchased().toString().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    else if(planning.getStatus().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                 
                    return false;
                
                });
            });
            SortedList<tblPlanningModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table_planning.comparatorProperty());
            table_planning.setItems(sortedData);
        
        });

    }
    
    
     void btnDashboard(MouseEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtama.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
    
    }
    
        @FXML
    void btnUser(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_dataUser.fxml"));
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
    private void add_planning(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_tambahPlanning.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    
}
