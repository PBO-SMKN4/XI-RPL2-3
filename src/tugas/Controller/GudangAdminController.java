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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import tugas.model.tblGudangModel;
import tugas.Main;
import tugas.help.DBConnect;
import tugas.help.DateUtil;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GudangAdminController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Circle myCircle;

    private double xOffset;
    private double yOffset;

    private Connection connection;
    private ObservableList<tblGudangModel> list;
    @FXML
    private TableView<tblGudangModel> table_gudang;
    @FXML
    private TableColumn<tblGudangModel, String> col_id;
    @FXML
    private TableColumn<tblGudangModel, String> col_namaitem;
    @FXML
    private TableColumn<tblGudangModel, String> col_brand;
    @FXML
    private TableColumn<tblGudangModel, String> col_category;
    @FXML
    private TableColumn<tblGudangModel, LocalDate> col_date;
    @FXML
    private TableColumn<tblGudangModel, Integer> col_qty;
    @FXML
    private TableColumn<tblGudangModel, Double> col_price;
    @FXML
    private TableColumn<tblGudangModel, Double> col_total;
    @FXML
    private TableColumn col_action;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        populateTableView();

        this.moveAnchorPane();
        myCircle.setStroke(Color.WHITE);
        Image img1 = new Image("/tugas/css/profil.jpg", false);
        myCircle.setFill(new ImagePattern(img1));

    }
   
  
    private void populateTableView() {
       
        try {
            list = FXCollections.observableArrayList();

            String query = "SELECT * FROM t_assets";
            connection = DBConnect.getKoneksi("localhost", "3306", "root", "", "db_sma");
            ResultSet rs = connection.createStatement().executeQuery(query);
 
            while (rs.next()) {

                tblGudangModel model = new tblGudangModel();
                model.setIdAsset(rs.getString("id_asset"));
                model.setNama_barang(rs.getString("nama_barang"));
                model.setBrand(rs.getString("brand"));
                model.setCategory(rs.getString("category"));
                model.setQty(rs.getInt("qty"));
                model.setUom(rs.getString("uom"));
                model.setLokasi_barang(rs.getString("lokasi_barang"));
                model.setJenis(rs.getString("jenis"));
                model.setPrice(rs.getDouble("price"));
                model.setTotal(rs.getDouble("total"));
                model.setKondisi(rs.getString("kondisi"));
                model.setTanggal_terima(DateUtil.parse(rs.getString("tanggal_terima")));
                model.setKeterangan(rs.getString("keterangan"));
                model.setFoto(rs.getString("foto"));

                list.add(model);
            }

            col_id.setCellValueFactory(cellData -> cellData.getValue().idAssetProperty());
            col_namaitem.setCellValueFactory(cellData -> cellData.getValue().nama_barangProperty());
            col_brand.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
            col_category.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
            col_date.setCellValueFactory(cellData -> cellData.getValue().tanggal_terimaProperty());
            col_qty.setCellValueFactory(cellData -> cellData.getValue().qtyProperty().asObject());
            col_price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
            col_total.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

            Callback<TableColumn<tblGudangModel, String>, TableCell<tblGudangModel, String>> cellFactory = (param) -> {
                final TableCell<tblGudangModel, String> cell = new TableCell<tblGudangModel, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            final Button edit = new Button("Detail");
                            edit.setOnAction(event -> {
                                tblGudangModel model = table_gudang.getSelectionModel().getSelectedItem();
                                if (model != null){
                                    boolean okClicked = Main.showBarangDetails(model);   
                                } 

                            });
                            setGraphic(edit);
                            setText(null);
                        }
                    }

                };

                return cell;
            };

            col_action.setCellFactory(cellFactory);

            table_gudang.setItems(list);

        } catch (SQLException ex) {
            Logger.getLogger(GudangAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void moveAnchorPane() {
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
    void add_item(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tugas/View/v_tambah.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
    }

}
