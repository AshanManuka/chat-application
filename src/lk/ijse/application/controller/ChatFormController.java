package lk.ijse.application.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ChatFormController {
    public AnchorPane chatContext;
    public TextArea chatBox;
    public Label boxNameLbl;
    public TextField typeField;
    public ImageView galleryId;

    public void galleryAction(MouseEvent mouseEvent) {
    System.out.println("clicked camera");
    }
}
