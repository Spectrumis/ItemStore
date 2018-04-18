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

        try {
            ItemIncomingOfferController item= ItemIncomingOfferController.LoadItem();
            item.setOffer(new ItemIncomingOffer(new Item("Battle axe", "Axe of man who battle", 145), 130));
            leftBox.getChildren().add(item.getRoot());

            item= ItemIncomingOfferController.LoadItem();
            item.setOffer(new ItemIncomingOffer(new Item("Ahmet Mizrak", "A mizrak, when its dealt it will confuse the enemy", 1024), 512));
            leftBox.getChildren().add(item.getRoot());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
