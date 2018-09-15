package Sockets;

import JSON.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    
    // Constructor
    public void Server(){
       Thread mihilo = new Thread(this);
		
       mihilo.start();
    }
    
    // Thread
    @Override
    public void run() {
        try{
            ServerSocket serverSocketListen = new ServerSocket (9999);
            
            Potatoe yehPotatoe;
            
            while (true){
                Socket inputSocket = serverSocketListen.accept();
                
                ObjectInputStream recievedData = new ObjectInputStream (inputSocket.getInputStream());
                yehPotatoe = (Potatoe) recievedData.readObject();
                
                //Potatoe thyPotatoe = JSONUtil.convertJsonToJava(yehPotatoe, Potatoe.class);
                System.out.println(yehPotatoe.getPrice());
                inputSocket.close();
            } 
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Main
    public static void main(String[] args){
        Server myServer = new Server();
    }
}