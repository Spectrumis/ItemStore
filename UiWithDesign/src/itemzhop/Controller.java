package itemzhop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Controller {
    @FXML private VBox leftBox;
    @FXML private TableView storeTable ;
    //storeTable içerisine items.csv içerisinde yer alan ürünlerin tamamını çekeceğiz ve her satırı bir eleman olarak ekleyeceğiz
    private void ahmetlen(ActionEvent event) {

        System.out.println("Ahmetlendiniz!");
    }

    @FXML
    public void initialize() {
        storeTable.setEditable(true);

        //burada bir while düşünelim items ın data filend ı kadar tabloda kolon oluşturulsun
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");
        storeTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        storeTable.refresh();

    }

    public void loadItems(){

    }
}
