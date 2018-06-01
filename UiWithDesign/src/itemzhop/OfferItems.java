package itemzhop;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.*;
public class OfferItems {

    @FXML
    private Label item_info  ;
    private TextField offer_price ;


    public void btn_back_clicked(javafx.event.ActionEvent actionEvent)  {
        //Load create item page

        try {
            Parent home_page_parent = FXMLLoader.load(getClass().getResource("mainFrame.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() throws IOException {
        BufferedReader getUserData = new BufferedReader(new FileReader("temp.csv"));
        String line = "";
        if ((line = getUserData.readLine()) != null) {
            //String[] tmpItemStr = line.split(",");
            item_info.setText(line);
        }
    }
    @FXML
    public void btn_offer_clicked(javafx.event.ActionEvent actionEvent) {
        if(null != offer_price.getText() &&  !offer_price.getText().isEmpty()){
            FileOpearation.AppendStringFile("", "TEMP",false);
            FileOpearation.AppendStringFile(offer_price.getText(), "TEMP",false);
        }


    }


}
