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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import tugas.Main;
import tugas.Controller.GudangAdminController;
import tugas.help.DateUtil;
import tugas.model.tblGudangModel;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class DetailBarangController implements Initializable  {
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Circle myCircle;
	
    private double xOffset;
    private double yOffset;
    @FXML
    private Text description;
    @FXML
    private Label namaitem;
    @FXML
    private Label price;
    @FXML
    private Label qty;
    @FXML
    private Label date;
    @FXML
    private Label category;
    @FXML
    private Label brand;
    @FXML
    private Label uom;
    @FXML
    private Label condition;
    @FXML
    private Label location ;
    @FXML
    private Label jenis;
    
    private Stage detailStage;
    private tblGudangModel gudang;
    private boolean okClicked = false;
    
    public void setDetailStage(Stage detailStage){
        this.detailStage = detailStage;
    }
    
    public void setDetail(tblGudangModel gudang){
        this.gudang = gudang;
        
          namaitem.setText(gudang.getNama_barang());
          brand.setText(gudang.getBrand());
          category.setText(gudang.getCategory());
          description.setText(gudang.getKeterangan());
          uom.setText(gudang.getUom());
          condition.setText(gudang.getKondisi());
          location.setText(gudang.getLokasi_barang());
          jenis.setText(gudang.getJenis());
          price.setText(Double.toString(gudang.getPrice()));
          qty.setText(Integer.toString(gudang.getQty()));
          date.setText(DateUtil.format(gudang.getTanggal_terima()));
          
        
    }
    
    public boolean isOkClicked(){
        return okClicked;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
