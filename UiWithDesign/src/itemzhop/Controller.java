package itemzhop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    @FXML private VBox leftBox;
    @FXML private TableView storeTable ;
    //storeTable içerisine items.csv içerisinde yer alan ürünlerin tamamını çekeceğiz ve her satırı bir eleman olarak ekleyeceğiz
    private void ahmetlen(ActionEvent event) {

        System.out.println("Ahmetlendiniz!");
    }

    @FXML
    public void initialize() throws IOException {
        storeTable.setEditable(true);


        //burada bir while düşünelim items ın data filend ı kadar tabloda kolon oluşturulsun


        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");
        storeTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<
                Item,String>("First Name"));


        BufferedReader getUserData = new BufferedReader(new FileReader("items.csv"));
        String[] tmpItemStr;
        String line;
        ArrayList<Item> myItem=new ArrayList<>( );
        Item tempItem=new Item();

        while ((line = getUserData.readLine()) != null) {
            tmpItemStr = line.split(",");
            tempItem.name=tmpItemStr[0].toString();
            tempItem.description=tmpItemStr[1].toString();
            tempItem.defaultPrice=0;
            myItem.add(tempItem);
            System.out.println(tempItem.toString());


        }



        storeTable.getItems().add(myItem);
        storeTable.refresh();

    }

    public void loadItems(){

    }
}
