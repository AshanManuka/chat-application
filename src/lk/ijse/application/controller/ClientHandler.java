package lk.ijse.application.controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String clientName;
    private String message;

    public ClientHandler(Socket socket) {
        System.out.println("handler ekta awa");
        try {
            System.out.println("handler eke try eke");
            this.socket = socket;
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.clientName = dataInputStream.readUTF();
            this.message = dataInputStream.readUTF();
            clientHandlers.add(this);
            /*String msg = dataInputStream.readUTF();
            System.out.println("msg in the handler :  "+msg);*/
            System.out.println("/    /");
            broadcastMessage(clientName+" is connected to the chat...!");
        } catch (IOException e) {
            closeAll(socket);
        }
    }

    @Override
    public void run() {
        System.out.println("In run method ");

        String messageFromClient;

        while (socket.isConnected()){
            try {
                messageFromClient =dataInputStream.readUTF();
                System.out.println("msg in the run method : "+messageFromClient);
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeAll(socket);
                break;
            }
        }

    }

    public void broadcastMessage(String message){
        System.out.println("in broadcast message : "+message);
        serch();
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                System.out.println("in handler.broadcast.try & begin "+message);
               // if (!clientHandler.clientName.equals(clientName)){
                    System.out.println("name is equals");
                    String msg = clientHandler.message;
                    clientHandler.dataOutputStream.writeUTF(message.trim());
                    dataOutputStream.writeUTF(msg.trim());
                    clientHandler.dataOutputStream.flush();
                //}
            } catch (IOException e) {
                closeAll(socket);
            }
        }
    }

    private void serch() {
        for (ClientHandler clientHandler:clientHandlers) {
            Socket s = clientHandler.socket;
            System.out.println("in for each : "+s);
            String name = clientHandler.clientName;
            System.out.println("client name is : "+name);
            String msg = clientHandler.message;
            System.out.println("client mesage is : "+msg);


        }
    }

    public void removeClientHandler (){
        clientHandlers.remove(this);
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
