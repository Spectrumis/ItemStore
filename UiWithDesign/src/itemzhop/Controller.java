package itemzhop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Controller {
    @FXML private VBox leftBox;

    @FXML
    private void ahmetlen(ActionEvent event) {
        System.out.println("Ahmetlendiniz!");
    }

    @FXML
    public void initialize() {
        System.out.println("initied");


    }
}
