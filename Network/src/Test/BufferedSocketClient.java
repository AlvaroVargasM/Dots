package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import JSON.*;
 
public class BufferedSocketClient {
    
    public static void main(String[] args) throws Exception {
        // Creates an object of a class
        Potatoe myPotatoe = new Potatoe();
        myPotatoe.setPrice(150);
        myPotatoe.setWeight(1);
        myPotatoe.setType("Bintje");
        
        // Converts the object into a JSON String
        String sendPotatoe = JSONUtil.convertJavaToJson(myPotatoe);

        // Opens a socket
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 1777);

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(sendPotatoe);
    
        while ((sendPotatoe = in.readLine()) != null) {
            System.out.println(sendPotatoe);
            out.println("bye");

            if (sendPotatoe.equals("bye"))
                break;
        }
        in.close();
        out.close();
        clientSocket.close();
    } 
}