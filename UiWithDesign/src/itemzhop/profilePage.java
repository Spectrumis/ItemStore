package itemzhop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class profilePage {

    @FXML
    Button btn_arkadaslar=new Button();
    @FXML
    private TableView<TableItems> tbl_profile = new TableView<>() ;
    @FXML private ListView<Item> listView;

    @FXML private TextField txt_search;
    ArrayList<Item> myItem=new ArrayList<>( );


    ObservableList data = FXCollections.observableList(myItem);



    //storeTable içerisine items.csv içerisinde yer alan ürünlerin tamamını çekeceğiz ve her satırı bir eleman olarak ekleyeceğiz


    @FXML
    public void loadFriends() throws IOException {

        final ObservableList<TableItems> data =
                FXCollections.observableArrayList();

        BufferedReader getUserData = new BufferedReader(new FileReader("accounts.csv"));
        String[] tmpItemStr;
        String line;
        Item tempItem;

        while ((line = getUserData.readLine()) != null) {
            tmpItemStr = line.split(",");
            tempItem=new Item();
            tempItem.name=tmpItemStr[0];
            tempItem.description=tmpItemStr[2];
            tempItem.defaultPrice=0;
            myItem.add(tempItem);

            data.add(new TableItems(tmpItemStr[1],tmpItemStr[3],100,5000,"1000"));

        }

        TableColumn itemName = new TableColumn("Kullanıcı Adı");
        itemName.setMinWidth(100);
        itemName.setCellValueFactory(
                new PropertyValueFactory<TableItems, String>("name"));

        TableColumn seller = new TableColumn("Kullanıcı Türü");
        seller.setMinWidth(100);
        seller.setCellValueFactory(
                new PropertyValueFactory<TableItems, String>("seller"));



        tbl_profile.setEditable(true);
        tbl_profile.getColumns().addAll(itemName, seller);
        tbl_profile.getItems().addAll(data);
        btn_arkadaslar.setDisable(true);

    }
}
