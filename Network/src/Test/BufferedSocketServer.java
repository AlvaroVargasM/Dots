package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import JSON.*;

public class BufferedSocketServer {

    public static void main(String[] args) throws Exception{
        int cTosPortNumber = 1777;
        String recievedPotatoe;

        // Creates a socket an
        ServerSocket servSocket = new ServerSocket(cTosPortNumber);
        System.out.println("Waiting for a connection on " + cTosPortNumber);

        Socket fromClientSocket = servSocket.accept();
        
        PrintWriter out = new PrintWriter(fromClientSocket.getOutputStream(), true);

        BufferedReader in = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));

        while ((recievedPotatoe = in.readLine()) != null) {
            System.out.println("The message: " + recievedPotatoe);

            if (recievedPotatoe.equals("bye")) {
                out.println("bye");
                break;
            } 
            else {
                Potatoe yehPotatoe = JSONUtil.convertJsonToJava(recievedPotatoe, Potatoe.class);
                recievedPotatoe = "Server returns " + yehPotatoe.getPrice()+ " " + yehPotatoe.getWeight();
                out.println(recievedPotatoe);
            }
        }
    out.close();
    in.close();

    fromClientSocket.close();
    }   
}