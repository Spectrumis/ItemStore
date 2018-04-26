package itemzhop;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable{

    @FXML private PasswordField txt_password;
    @FXML private TextField txt_username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonAction(ActionEvent actionEvent) throws IOException {


        BufferedReader getUserData = new BufferedReader(new FileReader("accounts.csv"));
        String[] tmpUser;
        String line;
        while ((line = getUserData.readLine()) != null) {
            tmpUser = line.split(",");
            if (tmpUser[0].equals(txt_username.getText()) && tmpUser[1].equals(txt_password.getText())){
                System.out.println("Succesfull!!");
                Parent home_page_parent= FXMLLoader.load(getClass().getResource("mainFrame.fxml"));
                Scene home_page_scene=new Scene(home_page_parent);
                Stage app_stage=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }
        }
    }
}
