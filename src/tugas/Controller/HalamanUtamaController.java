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
import java.sql.Statement;
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
import javafx.scene.control.Label;
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
import tugas.help.DBConnect;

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
    @FXML
    private Label total_item;
    @FXML
    private Label total_return;
    @FXML
    private Label total_user;
    @FXML
    private Label total_borrow;
    @FXML
    private Label total_report;
    @FXML
    private Label total_planning;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.moveAnchorPane();
        fill();

        myCircle1.setStroke(Color.WHITE);
        Image img1 = new Image("/tugas/css/profil.jpg", false);
        myCircle1.setFill(new ImagePattern(img1));
    }

    public void fill() {

        try {

            Connection connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
            String query = "SELECT COUNT(id_asset) as total FROM t_assets";
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);
            String query2 = "SELECT COUNT(username) as total FROM t_login where status = 'user'";
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query2);
            String query3 = "SELECT COUNT(id_report) as total FROM t_report";
            Statement stmt3 = connection.createStatement();
            ResultSet rs3 = stmt3.executeQuery(query3);
            String query4 = "SELECT COUNT(id_planning) as total FROM t_planning";
            Statement stmt4 = connection.createStatement();
            ResultSet rs4 = stmt4.executeQuery(query4);

            while (rs1.next()) {
                total_item.setText(rs1.getString("total"));

            }
            while (rs2.next()) {
                total_user.setText(rs2.getString("total"));
            }
            while (rs3.next()) {

                total_report.setText(rs3.getString("total"));
            }
            while (rs4.next()) {
                total_planning.setText(rs4.getString("total"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(HalamanUtamaController.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    @FXML
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

    void add_admin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_registeradmin.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    void exit(MouseEvent event) throws IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("Logout");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_Login.fxml"));
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

    public void moveAnchorPane() {
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
    private void btnDashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_halamanUtama.fxml"));
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void show_item(MouseEvent event) {
    }

    @FXML
    private void show_return(MouseEvent event) {
    }

    @FXML
    private void show_user(MouseEvent event) {
    }

    @FXML
    private void show_borrow(MouseEvent event) {
    }

    @FXML
    private void show_report(MouseEvent event) {
    }

    @FXML
    private void show_planning(MouseEvent event) {
    }

}
