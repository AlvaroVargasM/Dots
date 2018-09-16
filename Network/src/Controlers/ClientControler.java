package Controlers;

import JSON.JSONUtil;
import JSON.Potatoe;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class ClientControler {
    public static void main (String[] args) throws Exception{
        // Creates an object of a class

        Potatoe myPotatoe = new Potatoe();
        myPotatoe.setPrice(400);
        myPotatoe.setType("Amarilla");
        myPotatoe.setWeight(1);
       
        Employee myEmployee = new Employee();
        myEmployee.setName("Jebus");
        myEmployee.setSalary(69420);
        myEmployee.setEmpNo(506);
        
        ClientControler myControler = new ClientControler();
        myControler.clientSend(myPotatoe, myEmployee);
    }
   
    public void clientSend(Object object1, Object object2) throws Exception {
        
        // Converts the object into a JSON String
        String sendObject1 = JSONUtil.convertJavaToJson(object1);
        String sendObject2 = JSONUtil.convertJavaToJson(object2);

        // Opens a socket
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 1777);

        // Creates 
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println(sendObject1);
        out.println(sendObject2);
        
        /*while ((sendObject1 = in.readLine()) != null) {
            System.out.println(sendObject1);
            out.println("bye");

            if (sendObject1.equals("bye"))
                break;
        }*/
        in.close();
        out.close();
        clientSocket.close();
    } 
    
    public void clientRecive() throws Exception{
        
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
                recievedString = "Server returns " + recievedObject.getType()+ " " + recievedObject.getPrice();
                out.println(recievedString);
            }
        }
    out.close();
    in.close();

    fromClientSocket.close();
    }   
}