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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tugas.Main;
import tugas.help.DBConnect;
import tugas.model.tblPengembalianModel;
import tugas.model.tblUserModel;

/**
 * FXML Controller class
 *
 * @author Fadillah
 */
public class DataUserController implements Initializable {
    
    Connection koneksi = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");

    ObservableList<tblUserModel> oblist = FXCollections.observableArrayList();
    
    private final static int rowsPerPage = 10;    
    
    @FXML
    private AnchorPane anchorPane;
	
    private double xOffset;
    private double yOffset;
    @FXML
    private Circle myCircle;
    @FXML
    private TableColumn<?, ?> col_action;
    @FXML
    private Button add_user;
    @FXML
    private TableView<tblUserModel> table;
    @FXML
    private TableColumn<?, ?> col_no;
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
        showData();
        this.moveAnchorPane();

        myCircle.setStroke(Color.WHITE);
        Image img1 = new Image("/tugas/css/profil.jpg", false);
        myCircle.setFill(new ImagePattern(img1));
        
        
//        pagination = new Pagination((oblist.size() / rowsPerPage + 1), 0);
//        pagination.setPageFactory(this::createPage);
        
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<tblUserModel> filteredData = new FilteredList<>(oblist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		inpSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (user.getFullname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (user.getKelas().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				else  
                                    return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<tblUserModel> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }  

    private void showData(){
        try{
            Statement stmt = (Statement) koneksi.createStatement();
            String query = "SELECT * FROM t_login WHERE status = 'user'";
            ResultSet rs = stmt.executeQuery(query);
            
            int no = 1;
            while(rs.next()){
                oblist.add(new tblUserModel(rs.getString("username"),rs.getString("fullname"), "-", rs.getString("email") ));     
            }
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        col_class.setCellValueFactory(new PropertyValueFactory<>("kelas"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        table.setItems(oblist);
    }
    @FXML
    void btnDashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtama.fxml"));
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
    void btnReport(MouseEvent event) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_report.fxml"));
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
    
    
    void add_user(MouseEvent event) throws IOException{
            Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_registeradmin.fxml"));
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
            //action
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
