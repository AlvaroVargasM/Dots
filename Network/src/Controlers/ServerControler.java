package Controlers;

import JSON.JSONUtil;
import JSON.Potatoe;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerControler {
    
    public static void main (String[] args) throws Exception{
        ServerControler myControler = new ServerControler();
        myControler.serverRecive();
    }
    
     public void ServerSend(Object object) throws Exception{
  
        // Converts the object into a JSON String
        String sendObject = JSONUtil.convertJavaToJson(object);

        // Opens a socket
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 1777);

        // Creates 
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(sendObject);
    
        while ((sendObject = in.readLine()) != null) {
            System.out.println(sendObject);
            out.println("bye");

            if (sendObject.equals("bye"))
                break;
        }
        in.close();
        out.close();
        clientSocket.close();
    } 
    
    public void serverRecive() throws Exception{
        int cTosPortNumber = 1777;
        String recievedString;

        // Creates a socket an
        ServerSocket servSocket = new ServerSocket(cTosPortNumber);
        System.out.println("Waiting for a connection on " + cTosPortNumber);

        Socket fromClientSocket = servSocket.accept();
        
        PrintWriter out = new PrintWriter(fromClientSocket.getOutputStream(), true);

        BufferedReader in = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));

        while ((recievedString = in.readLine()) != null) {
            System.out.println("The message: " + recievedString);

            if (recievedString.equals("bye")) {
                out.println("bye");
                break;
            } 
            else {
                Potatoe recievedObject = JSONUtil.convertJsonToJava(recievedString, Potatoe.class);
                recievedString = "Server returns " + recievedObject.getPrice()+ " " + recievedObject.getWeight();
                out.println(recievedString);
            }
        }
    out.close();
    in.close();

    fromClientSocket.close();
    }
}