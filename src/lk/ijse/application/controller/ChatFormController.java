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
    public static int PORT;

    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String name = " ";
    String message = " ";
    String reply = " ";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boxNameLbl.setText(chatName);
        makeNewChat();
        /*new Thread(() -> {
            try {
                socket = new Socket("localhost", PORT);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("exit")) {
                    reply = dataInputStream.readUTF();
                    System.out.println("server :  "+reply);
                    chatBox.appendText("\n "+"server :  "+reply);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

    private void makeNewChat() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", PORT);
                System.out.println("client first port : "+ PORT);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("exit")) {
                    reply = dataInputStream.readUTF();
                    System.out.println("server :  "+reply);
                    chatBox.appendText("\n "+"server :  "+reply);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void galleryAction(MouseEvent mouseEvent) {
        System.out.println("clicked camera");
    }

    public void sendMessage(ActionEvent actionEvent) throws IOException {
        goMessage();
    }

    public void goMessage() throws IOException {
        dataOutputStream.writeUTF(typeField.getText().trim());
        dataOutputStream.flush();
        typeField.clear();

/*        PORT = PORT+1;
        System.out.println("new port is : "+PORT);*/
    }

    public void enterEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            goMessage();
        }
    }
}