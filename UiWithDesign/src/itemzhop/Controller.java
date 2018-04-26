package itemzhop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import javax.xml.soap.Text;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML private VBox leftBox;
    @FXML private TableView<Item> storeTable ;
    private ObservableList data;
   @FXML private ListView<Item> listView;


    
    //storeTable içerisine items.csv içerisinde yer alan ürünlerin tamamını çekeceğiz ve her satırı bir eleman olarak ekleyeceğiz
    private void ahmetlen(ActionEvent event) {

        System.out.println("Ahmetlendiniz!");
    }

    @FXML
    public void initialize() throws IOException {


        BufferedReader getUserData = new BufferedReader(new FileReader("items.csv"));
        String[] tmpItemStr;
        String line;
        ArrayList<Item> myItem=new ArrayList<>( );
        Item tempItem;

        while ((line = getUserData.readLine()) != null) {
            tmpItemStr = line.split(",");
            tempItem=new Item();
            tempItem.name=tmpItemStr[0];
            tempItem.description=tmpItemStr[1];
            tempItem.defaultPrice=0;
            myItem.add(tempItem);
        }
        ObservableList data = FXCollections.observableList(myItem);

        listView.setItems(data);



    }

    public void loadItems() throws IOException {








    }


}
