/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import tugas.Main;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GudangUserController implements Initializable {
    
        @FXML
    private AnchorPane anchorPane;
    
@FXML	
    private double xOffset;
@FXML
    private double yOffset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.moveAnchorPane();
         
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
      
    
}
