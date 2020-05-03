package tugas;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import tugas.Controller.DetailBarangController;
import tugas.Controller.EditProfilUserController;
import tugas.model.tblGudangModel;
import tugas.model.tblUserModel;

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);

    }

    public static Stage getPrimaryStage() {
        return primaryStageObj;
    }
    
    public static boolean showBarangDetails(tblGudangModel gudang){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/tugas/View/v_detailBarang.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage detailsStage = new Stage();
            Scene scene = new Scene(page);
           detailsStage.setScene(scene);
            
            DetailBarangController controller = loader.getController();
            controller.setDetailStage(detailsStage);
            controller.setDetail(gudang);
            
            detailsStage.showAndWait();
            
            return controller.isOkClicked();
            
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean showEditDataUser(tblUserModel user){
        try{
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/tugas/View/v_editProfilUser.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage detailsStage = new Stage();
            Scene scene = new Scene(page);
           detailsStage.setScene(scene);
            
            EditProfilUserController controller = loader.getController();
            controller.setDetailStage(detailsStage);
            controller.setEdit(user);
          
            
            detailsStage.showAndWait();
            
            return controller.isOkClicked();
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
