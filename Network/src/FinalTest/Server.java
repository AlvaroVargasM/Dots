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

public class Server implements Runnable{

    public static void main(String[] args) {
        Thread mihilo = new Thread( new Server());	
        mihilo.start();
        
    }
    public void serverSend (Object object, Object classReference){
         try {
            Socket serverAsClientSocket = new  Socket(InetAddress.getLocalHost(), 9099);
            
            String sendObject = JSONUtil.convertJavaToJson(object);
            String sendClassReference = JSONUtil.convertJavaToJson(classReference);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(serverAsClientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(serverAsClientSocket.getOutputStream(), true);
            
            out.println(sendObject);
            out.println(sendClassReference);
            
            in.close();
            out.close();
            serverAsClientSocket.close();
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
            
            ServerSocket servSocket = new ServerSocket(cTosPortNumber);
            
            System.out.println("Server waiting for a connection on " + cTosPortNumber);
            
            while (true){
                //ServerSocket servSocket = new ServerSocket(cTosPortNumber);
                Socket fromClientSocket = servSocket.accept();
                
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
                         
                         Potatoe potatoe4send = new Potatoe();
                         potatoe4send.setPrice(300);
                         potatoe4send.setType("yeet");
                         potatoe4send.setWeight(69);
                         
                         ClassReference pReference = new ClassReference();
                         pReference.setReference("Potatoe");
                         serverSend(potatoe4send,pReference);
                         break;
                    }
                    if (reference.getReference().equals("Employee")){
                        Employee recievedEmployee = JSONUtil.convertJsonToJava(recievedObjectAsString, Employee.class);
                        System.out.println(recievedEmployee);
                        break;
                        
                    }
                    else{
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}