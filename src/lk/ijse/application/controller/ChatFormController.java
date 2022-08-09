package lk.ijse.application.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatFormController implements Initializable {
    public AnchorPane chatContext;
    public TextArea chatBox;
    public Label boxNameLbl;
    public TextField typeField;
    public ImageView galleryId;
    public JFXButton sendBtn;
    public static String chatName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boxNameLbl.setText(chatName);

    }



    public void galleryAction(MouseEvent mouseEvent) {
        System.out.println("clicked camera");
    }

    public void sendMessage(ActionEvent actionEvent) throws IOException {
        goMessage();
    }

    public void goMessage() throws IOException {
        typeField.clear();

    }

    public void enterEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            goMessage();
        }
    }
}