/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    
    @FXML
    private Circle myCircle;
	
    private double xOffset;
    private double yOffset;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	this.moveAnchorPane();
                
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
    
    @FXML
    void add_admin(MouseEvent event) throws IOException{
            Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_registeradmin.fxml"));
            
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.setScene(new Scene(root));
    }
    
    @FXML
    void exit(MouseEvent event) throws IOException{
            Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_Login.fxml"));
            
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
}
