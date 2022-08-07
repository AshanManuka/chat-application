package lk.ijse.application.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatFormController implements Initializable {
    public AnchorPane chatContext;
    public TextArea chatBox;
    public Label boxNameLbl;
    public TextField typeField;
    public ImageView galleryId;
    public static String chatName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boxNameLbl.setText(chatName);
    }

    public void galleryAction(MouseEvent mouseEvent) {
    System.out.println("clicked camera");
    }

}
