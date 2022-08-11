package lk.ijse.application.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServerClass implements Initializable {
    public JFXButton startBtn;
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message= "";
    String reply = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(2000);
                System.out.println("Server Started..");
                socket = serverSocket.accept();
                System.out.println("Client is connected...!");

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());


                System.out.println("socket after stream : "+socket);

                while (socket.isConnected()) {
                    message = dataInputStream.readUTF();
                    System.out.println("Client : " + message);
                    dataOutputStream.writeUTF(message.trim());
                    dataOutputStream.flush();
                }

                System.out.println("socket in try : "+socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("socket in thred : "+socket);
        }).start();

        System.out.println("socket in method : "+socket);
    }


    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void startAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(parent));
        stage1.show();

        Stage stage2 = (Stage) startBtn.getScene().getWindow();
        stage2.close();

        }

}
