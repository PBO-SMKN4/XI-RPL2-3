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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import tugas.model.tblGudangModel;
import tugas.Main;
import tugas.help.DBConnect;
import tugas.help.DateUtil;
import tugas.model.tblUserModel;

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
    @FXML
    private TextField inpsearch;

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

                    private final Button edit = new Button("EDIT");
                    private final Button detail = new Button("Detail");
                    private final Button delete = new Button("Delete");
                    private final HBox pane = new HBox(edit, detail, delete);

                    {
                        edit.setOnAction((ActionEvent event) -> {
                            tblGudangModel model = table_gudang.getSelectionModel().getSelectedItem();
                            if (model != null) {
                                boolean okClicked = Main.showEditDetails(model);
                            } else {
                                System.out.println("Jai");
                            }
                        });

                        detail.setOnAction((ActionEvent event) -> {
                            //tblGudangModel model = getTableView().getItems().get(getIndex());
                            tblGudangModel model = table_gudang.getSelectionModel().getSelectedItem();
                            if (model != null) {
                                boolean okClicked = Main.showBarangDetails(model);
                            }

                        });

                        delete.setOnAction((ActionEvent event) -> {
                            try {
                                tblGudangModel model = getTableView().getItems().get(getIndex());
                                String query2 = "delete from t_transaksi where id_asset = ?";
                                PreparedStatement pst2 = connection.prepareStatement(query2);
                                pst2.setString(1, model.getIdAsset());
                                pst2.executeUpdate();

                                String query = "delete from t_assets where id_asset = ?";
                                PreparedStatement pst = connection.prepareStatement(query);
                                pst.setString(1, model.getIdAsset());
                                pst.executeUpdate();

                            } catch (SQLException ex) {
                                Logger.getLogger(DataUserController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        setGraphic(empty ? null : pane);
                    }

                };

                return cell;
            };

            col_action.setCellFactory(cellFactory);

            table_gudang.setItems(list);

        } catch (SQLException ex) {
            Logger.getLogger(GudangAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FilteredList<tblGudangModel> filteredData = new FilteredList<>(list, e -> true);
        inpsearch.setOnKeyReleased(e -> {
            inpsearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super tblGudangModel>) gudang -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (gudang.getNama_barang().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (gudang.getIdAsset().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (gudang.getBrand().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (gudang.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (gudang.getJenis().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (gudang.getTanggal_terima().toString().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(gudang.getQty()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(gudang.getPrice()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(gudang.getTotal()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;

                });
            });
        });
        SortedList<tblGudangModel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_gudang.comparatorProperty());
        table_gudang.setItems(sortedData);

    }

    
            

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
    
    @FXML
    void exit(MouseEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
            //action
        }
    }

}
