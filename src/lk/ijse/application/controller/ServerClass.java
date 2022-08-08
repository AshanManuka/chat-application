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
import java.util.ResourceBundle;

public class ServerClass implements Initializable {
    public JFXButton startBtn;
    ServerSocket serverSocket;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    public static int newPort;
    public static boolean select;

    String name = " ";
    String message = " ";
    String reply = " ";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       if (select){
           makeHost();
       }


    }

    private void makeHost() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(newPort);
                System.out.println("server start....");
                accept = serverSocket.accept();
                System.out.println("Client connected............");

                dataInputStream = new DataInputStream(accept.getInputStream());
                dataOutputStream = new DataOutputStream(accept.getOutputStream());

                while (!message.equals("exit")){
                    message = dataInputStream.readUTF();
                    System.out.println("client :  "+message);
                    reply = message;

                    dataOutputStream.writeUTF(reply);
                    dataOutputStream.flush();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(select);
        select = false;
        System.out.println(select);
    }

   /* public int portMaker(int P){

        return 0;
    }*/

    public void startAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/mainForm.fxml"));
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(parent));
        stage1.show();

        Stage stage2 = (Stage) startBtn.getScene().getWindow();
        stage2.close();

        /* Make server port as 0*/
        newPort = 0;

    }

}
