package lk.ijse.application.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public AnchorPane mainContext;
    public JFXButton loginBtn;
    public TextField userName;
    public int port = 4999;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userName.requestFocus();
    }

    public void loginAction(ActionEvent actionEvent) throws IOException {
        goChatPage();
    }

    public void goChatPage() throws IOException {
        /*Port is increment*/
        port = port+1;
/*        System.out.println("making new client : "+port);*/
        ServerClass.newPort = port;
        ServerClass.select = true;
        ChatFormController.chatName = userName.getText();
        ChatFormController.PORT = port;
        userName.clear();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/chatForm.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(parent));
        stage1.show();
    }

    public void enterEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER){
            goChatPage();
        }
    }
}
