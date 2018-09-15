package Sockets;

import JSON.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main (String[] args){
        ClientSend myClient = new ClientSend();
    }
}  

// This class sends objects to a server
class ClientSend implements ActionListener  {
   
   @Override
   public void actionPerformed(ActionEvent var1) {
       try {
   
       // The object that we want to send is created
       Potatoe myPotatoe = new Potatoe();
       myPotatoe.setPrice(300);
       myPotatoe.setWeight(1);
       myPotatoe.setType("Bintje");
       
       // The object is converted into a JSON string
       String sendPotatoe = JSONUtil.convertJavaToJson(myPotatoe);
       
       // The object is sent through sockects to a server
       Socket clientSocketSend = new Socket("192.168.100.10",9999);
       
       /*
       ObjectOutputStream outputStream = new ObjectOutputStream(clientSocketSend.getOutputStream());	
       outputStream.writeObject(sendPotatoe);
       */
       /*
       OutputStreamWriter osw = OutputStreamWriter(clientSocketSend.getOutputStream(), "UTF-8");
       osw.write(sendPotatoe, 0, 0);
       */
       
       OutputStreamWriter osw = new OutputStreamWriter(clientSocketSend.getOutputStream(), StandardCharsets.UTF_8);
       osw.write(sendPotatoe);
      
       
       clientSocketSend.close();
       }
       catch (IOException e){
           System.out.println(e.getMessage());
       }
   }

    private OutputStreamWriter OutputStreamWriter(OutputStream outputStream, String utF8) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}        