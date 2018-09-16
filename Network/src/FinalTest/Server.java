package FinalTest;

import Controlers.Employee;
import JSON.JSONUtil;
import JSON.Potatoe;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable{

    public static void main(String[] args) {
        Server myServer = new Server();
        myServer.run();       
    }

    @Override
    public void run() {
        try {
            int cTosPortNumber = 9090;
            
            System.out.println("Waiting for a connection on " + cTosPortNumber);
            
            while (true){
                ServerSocket servSocket = new ServerSocket(cTosPortNumber);
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