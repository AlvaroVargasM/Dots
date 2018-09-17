package FinalTest;

import Controlers.Employee;
import JSON.JSONUtil;
import JSON.Potatoe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable{
    public static void main(String[] args) {
        Potatoe myPotatoe = new Potatoe();
        myPotatoe.setPrice(600);
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

    @Override
    public void run() {
       try {
            int cTosPortNumber = 9090;
            
            System.out.println("Waiting for a connection on " + cTosPortNumber);
            
            while (true){
                ServerSocket clientAsServer = new ServerSocket(cTosPortNumber);
                Socket fromClientSocket = clientAsServer.accept();
                
                PrintWriter out = new PrintWriter(fromClientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));
                
                String recievedObjectAsString = in.readLine();
                String recievedClassReferenceAsString = in.readLine();
                
                out.close();
                in.close();
                fromClientSocket.close();
                
                while (recievedObjectAsString != null && recievedClassReferenceAsString != null){
                    ClassReference reference = JSONUtil.convertJsonToJava(recievedClassReferenceAsString, ClassReference.class);
                    if (reference.getReference().equals("Potatoe")){
                         Potatoe recievedPotatoe = JSONUtil.convertJsonToJava(recievedObjectAsString, Potatoe.class);
                         System.out.println(recievedPotatoe.getPrice());
                         break;
                    }
                    if (reference.getReference().equals("Employee")){
                        Employee recievedEmployee = JSONUtil.convertJsonToJava(recievedObjectAsString, Employee.class);
                        System.out.println(recievedEmployee);
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}