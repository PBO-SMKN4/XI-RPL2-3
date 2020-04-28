/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tugas.Main;

/**
 * FXML Controller class
 *
 * @author Roshan
 */
public class HalamanUtamaController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
	
    private double xOffset;
    private double yOffset;
    @FXML
    private Circle myCircle1;
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {	
        this.moveAnchorPane();

        myCircle1.setStroke(Color.WHITE);
        Image img1 = new Image("/tugas/css/profil.jpg", false);
        myCircle1.setFill(new ImagePattern(img1));
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
    
    
    void add_admin(MouseEvent event) throws IOException{
            Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_registeradmin.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
    }
    
    @FXML
    void exit(MouseEvent event) throws IOException{
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
