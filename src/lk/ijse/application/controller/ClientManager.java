package lk.ijse.application.controller;

import javafx.fxml.Initializable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientManager implements Runnable, Initializable {
    public static ArrayList<ClientManager> clientManagers = new ArrayList<>();
    private Socket socket;
    private String inMessage;
    private String outMessage;
    private String clientName;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;



    public ClientManager(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.clientName  =  dataInputStream.readUTF();
        System.out.println("added : "+clientName);

    }

    @Override
    public void run() {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("client Manager is working...");
    }
}
