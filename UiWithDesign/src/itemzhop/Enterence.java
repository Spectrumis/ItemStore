package itemzhop;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sun.misc.FormattedFloatingDecimal;

import javax.swing.*;
import java.text.Normalizer;

public class Enterence extends Application{
    @FXML private TextField txt_userName;
    @FXML private PasswordField txt_password;
    @FXML private Button btn_Enterence;
    @FXML private DialogPane paneEnterence;


    public Enterence(){
        /*txt_userName=new TextField();
        txt_Password=new PasswordField();
        paneEnterence=new DialogPane();
        btn_Enterence=new Button();*/
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("enterence.fxml"));
        primaryStage.setTitle("Project Item Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public String enter() {


            System.out.println(txt_userName.getText().toString());
        System.out.println(txt_password.getText().toString());

        if (txt_userName.getText().equals("mesut") && txt_password.getText().equals("123")){

            }


        return "main.fxml";
    }
}
