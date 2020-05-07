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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tugas.Main;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class HalamanUtamaUserController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    private double xOffset;
    private double yOffset;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.moveAnchorPane();
                

//        System.out.println(userLogin.stringsList.get(1));
//        System.out.println(userLogin.session.getUserName());
//        System.out.println(LoginController.getIdKelas());
//        System.out.println(LoginController.getUsername());
//        
//        //System.out.println(userLogin.getNama());
//        lblName.setText(LoginController.getNama());
    }
    
    
    
//        Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/.fxml"));
//        Node node = (Node) event.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
//        stage.setScene(new Scene(root));
//    }
    
    
    @FXML
    private void btnBorrow(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_pinjamBarang.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
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

    @FXML
    private void btnHome(MouseEvent event) {
    }


    
    
    
   
}
