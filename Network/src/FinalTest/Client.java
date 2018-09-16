package FinalTest;

import JSON.JSONUtil;
import JSON.Potatoe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        Potatoe myPotatoe = new Potatoe();
        myPotatoe.setPrice(400);
        myPotatoe.setType("Amarilla");
        myPotatoe.setWeight(1);
        
        ClassReference myClassReference = new ClassReference();
        myClassReference.setReference("Potatoe");
        
        Client myClient = new Client();
        myClient.clientSend(myPotatoe, myClassReference);
    }
    public void clientSend(Object object, Object classReference){
        try {
            Socket clientSocket = new  Socket(InetAddress.getLocalHost(), 9090);
            
            String sendObject = JSONUtil.convertJavaToJson(object);
            String sendClassReference = JSONUtil.convertJavaToJson(classReference);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            out.println(sendObject);
            out.println(sendClassReference);
            
            in.close();
            out.close();
            clientSocket.close();
        } 
        catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}