package lk.ijse.application.controller;

import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable{
    public static ArrayList<ClientManager> clientHandlers = new ArrayList<>();
    private Socket socket;
    private String clientName;


    public ClientManager(Socket socket) {
    System.out.println("client manager constructor : "+socket);
    }

    @Override
    public void run() {

    }
}
