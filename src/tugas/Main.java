package tugas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;


public class Main extends Application {
	
	private static Stage primaryStageObj;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStageObj = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
                        primaryStage.setResizable(true);
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
                        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
                
                
	}
	
	  public static Stage getPrimaryStage() {
	        return primaryStageObj;
	  }
}
