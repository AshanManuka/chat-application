package lk.ijse.application.controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {
    public static ArrayList<ClientManager> clientManagers = new ArrayList<>();
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String clientName;
    private String message;

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.clientName = MainFormController.name;
            clientManagers.add(this);

            broadcastMessage(clientName+" is connected to the chat...!");
        } catch (IOException e) {
            closeAll(socket);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()){
            try {
                messageFromClient =dataInputStream.readUTF();
                System.out.println("in run method : "+messageFromClient);
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeAll(socket);
                break;
            }
        }

    }

    public void broadcastMessage(String message){
        search();
        for (ClientManager clientManager : clientManagers) {
            try {
                    clientManager.dataOutputStream.writeUTF(message+"\n");
                    clientManager.dataOutputStream.flush();
            } catch (IOException e) {
                closeAll(socket);
            }
        }
    }

    private void search() {
        for (ClientManager clientManager : clientManagers) {
            Socket s = clientManager.socket;
            System.out.println("in for each : "+s);
            String name = clientManager.clientName;
            System.out.println("client name is : "+name);
            String msg = clientManager.message;
            System.out.println("client mesage is : "+msg);
        }
    }

    public void removeClientHandler (){
        clientManagers.remove(this);
        broadcastMessage("SERVER: "+clientName+" has left from the chat");
    }

    private void closeAll(Socket socket) {
        removeClientHandler();

        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
