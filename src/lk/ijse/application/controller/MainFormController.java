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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public AnchorPane mainContext;
    public JFXButton loginBtn;
    public TextField userName;
    ServerSocket serverSocket;
    Socket socket;
    public static String name;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userName.requestFocus();

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(2000);
                System.out.println("Server Started..");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        }



    public void loginAction(ActionEvent actionEvent) throws IOException {
        goChatPage();
    }

    public void goChatPage() throws IOException {
        name = userName.getText();

        ChatFormController.chatName = userName.getText();
        userName.clear();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/chatForm.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(parent));
        stage1.show();


        new Thread(() -> {
            try {
                socket = serverSocket.accept();
                System.out.println("Client is connected...!");

                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }).start();
    }

    public void enterEvent(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER){
            goChatPage();
        }
    }
}
