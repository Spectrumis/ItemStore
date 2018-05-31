package itemzhop;

import itemzhop.Algorithms.BinarySearchTree;
import itemzhop.Algorithms.RedBlackTree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Controller {
    @FXML
    private VBox leftBox;
    @FXML
    private ComboBox<String> sortSelect = new ComboBox<String>();
    @FXML
    private TableView<TableItems> storeTable = new TableView<>();
    private ArrayList<Sales> shopItemsL = new ArrayList<>();
    @FXML
    private ListView<Item> listView;
    @FXML
    private TextField txt_search;


    RedBlackTree<Sales> shopItems = new RedBlackTree();
    BinarySearchTree tree = new BinarySearchTree();

    private void addItemsInTable(ObservableList<TableItems> data) {

        TableColumn itemName = new TableColumn("Ürün Adi");
        itemName.setMinWidth(100);
        itemName.setCellValueFactory(new PropertyValueFactory<TableItems, String>("name"));

        TableColumn seller = new TableColumn("Satıcı");
        seller.setMinWidth(100);
        seller.setCellValueFactory(new PropertyValueFactory<TableItems, String>("seller"));

        TableColumn price = new TableColumn("Teklif");
        price.setMinWidth(20);
        price.setCellValueFactory(new PropertyValueFactory<TableItems, String>("price"));


        TableColumn maxPrice = new TableColumn("Son Fiyat");
        maxPrice.setMinWidth(20);
        maxPrice.setCellValueFactory(new PropertyValueFactory<TableItems, String>("maxPrice"));


        TableColumn time = new TableColumn("Kalan Süre");
        time.setMinWidth(50);
        time.setCellValueFactory(new PropertyValueFactory<TableItems, String>("time"));


        // Table cell coloring
        time.setCellFactory(new Callback<TableColumn<TableItems, String>, TableCell<TableItems, String>>() {
            @Override
            public TableCell<TableItems, String> call(TableColumn<TableItems, String> param) {
                return new TableCell<TableItems, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setTextFill(Color.RED);
                            // Get fancy and change color based on data
                            if (item.contains("@"))
                                this.setTextFill(Color.BLUEVIOLET);
                            setText(item);

                        }
                    }

                };
            }
        });


        storeTable.setEditable(true);
        storeTable.getColumns().addAll(itemName, seller, price, maxPrice, time);
        storeTable.getItems().addAll(data);


    }


    @FXML
    public void initialize() throws IOException {

        final ObservableList<TableItems> data =
                FXCollections.observableArrayList();

        Random rand = new Random();
        BufferedReader getUserData = new BufferedReader(new FileReader("sales.csv"));
        String[] tmpItemStr;
        String line;
        Sales sale;

        while ((line = getUserData.readLine()) != null) {
            tmpItemStr = line.split(",");

            sale = createSale(tmpItemStr);
            shopItems.add(sale);
            shopItemsL.add(sale);

            data.add(new TableItems(sale.getItemName(),
                    sale.getSeller(),
                    sale.getPrice(),
                    sale.getMaxPrice(),
                    sale.getRemainingTime(), sale.getLastBidder()));
            tree.add(sale);
        }
        addItemsInTable(data);
    }

    /**
     * pars csv line and create Sales objecjt with this paramters
     *
     * @param tmpItemStr a line of csve file
     * @return Sales object that a line for a item's sale
     */
    private Sales createSale(String[] tmpItemStr) {
        long timeData;
        int time;       //time left as integer in seconds
        int seconds = 0;
        int minutes = 0;
        int hours = 0;
        int days = 0;

        NumberFormat formatter = new DecimalFormat("00");       //for time column


        timeData = Long.parseLong(tmpItemStr[5]);      //time convertion

        long currentTime = (new Date()).getTime() / 1000;


        timeData = timeData - currentTime;
        time = (int) timeData;
        if (timeData > 0) {

            seconds = (int) ((timeData) % 60);
            minutes = (int) ((timeData / 60) % 60);
            hours = (int) ((timeData / (60 * 60)) % 24);
            days = (int) ((timeData / (60 * 60 * 24)) % 7);
        }

        return new Sales(Integer.parseInt(tmpItemStr[0]),       //id
                tmpItemStr[1],                          //item name
                tmpItemStr[2],                          //seller
                Integer.parseInt(tmpItemStr[3]),        //price
                Integer.parseInt(tmpItemStr[4]),        //max price
                time,
                (days + " gün " + formatter.format(hours) + ":" +
                        formatter.format(minutes) + ":" + formatter.format(seconds)),         //time in seconds
                tmpItemStr[6]);                          //last bidder name
    }

    public void loadItems(ActionEvent actionEvent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("profilePage.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void btn_search_click() {

        storeTable.getItems().clear();
        ObservableList<TableItems> data =
                FXCollections.observableArrayList();

        if (txt_search.getText() == null || txt_search.getText().isEmpty()) {

            data = fillData(shopItemsL);

        } else {
            ArrayList<Sales> result = new ArrayList<>();
            result = shopItems.FindAll(new Sales(txt_search.getText()), shopItemsL);
            data = fillData(result);

        }
        storeTable.setItems(data);
        storeTable.refresh();
    }

    private ObservableList<TableItems> fillData(ArrayList<Sales> dataField) {
        ObservableList<TableItems> data =
                FXCollections.observableArrayList();

        for (Sales sale : dataField) {
            data.add(new TableItems(sale.getItemName(),
                    sale.getSeller(),
                    sale.getPrice(),
                    sale.getMaxPrice(),
                    sale.getRemainingTime(), sale.getLastBidder()));
        }
        return data;
    }

}