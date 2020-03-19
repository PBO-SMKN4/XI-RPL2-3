/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class HalamanUtamaUserController implements Initializable {
    
    @FXML
    private Circle myCircle1;
    
    @FXML
    private Circle myCircle2;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        myCircle1.setStroke(Color.BLACK);
        Image img1 = new Image("/tugas/Controller/profil.jpg", false);
        myCircle1.setFill(new ImagePattern(img1));
        
        myCircle2.setStroke(Color.BLACK);
        Image img2 = new Image("/tugas/Controller/profil.jpg", false);
        myCircle2.setFill(new ImagePattern(img2));
    }    
    
    @FXML
    void exit(MouseEvent event) throws IOException{
        Parent root =   FXMLLoader.load(getClass().getResource("/tugas/View/v_Login.fxml"));
            
            Node node = (Node) event.getSource();
            
            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.setScene(new Scene(root));
    }
    
}