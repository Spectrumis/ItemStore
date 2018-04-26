package itemzhop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class CreateAccount implements Initializable {
    @FXML private TextField txt_userName;
    @FXML private TextField txt_password;
    @FXML private TextField txt_passRepeat;


    public CreateAccount() throws IOException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createActionHandler(ActionEvent actionEvent) throws IOException {

        //csv dosyasında eşleşen kayıt yoksa ekleme yap.eşleşen kayıt varsa hata mesajı ver


        String csvFile = "accounts.csv";
        FileWriter writer = new FileWriter(csvFile, true);

        BufferedReader getUserData = new BufferedReader(new FileReader("accounts.csv"));
        String[] tmpUser;
        String line;
        boolean check=false;
        while ((line = getUserData.readLine()) != null) {
            tmpUser = line.split(",");
            if (tmpUser[0].equals(txt_userName.getText()) && tmpUser[1].equals(txt_password.getText())) {
                check=true;
                System.out.println("Bu kullanıcı zaten sistemde kayıtlı");

            }


        }


        if (txt_password.getText().equals(txt_passRepeat.getText()) && check==false) {

            writer.write(txt_userName.getText().toString() + "," + txt_password.getText().toString() + "\n");
            System.out.println("New Acoount Created!!");
        } else {
            System.out.println("Wrong Information!");
        }

        writer.close();

    }
}
