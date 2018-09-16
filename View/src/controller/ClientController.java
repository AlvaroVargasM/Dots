package controller;

import com.sun.security.ntlm.Client;
import dataPackages.ClassReference;
import jsonLogic.*;
import visualFrames.*;
import jsonLogic.JSONUtil;
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


public class ClientController {
    
    /**
     * Indicate if the user is player 1 or player 2.
     */
    private static int playerNumber = 0;
    
    
    
    /**
     * Player 1 name.
     */
    private static String p1Name;
    
    /**
     * Player 2 name.
     */
    private static String p2Name;
    
    /**
     * Player 1 score.
     */
    private static int p1Score = 0;
    
    /**
     * Player 2 score.
     */
    private static int p2Score = 0;
    
    /**
     * Holds the turn's number.
     */
    private static int TurnNumber = 1;
    
    /**
     * User's menu.
     */
    private static MenuFrame menu;
    /**
     * User's main game grid.
     */
    private static MainFrame game;
    /**
     * User's results window.
     */
    private static ResultsFrame results;
    
    private static boolean registered = false;
    private static boolean gameActive = false;
    
    public static void main (String[] args) throws Exception{
        
        menu = new MenuFrame();
        while(!registered){
            if(!(menu.getNickName().equals(""))){
               
                //send json to recieve playerNumber
                
                if(playerNumber == 0 ){
                    menu.standBy();
                }else{
                    if(playerNumber==1){
                        p1Name = menu.getNickName();
                    }else{
                        p2Name = menu.getNickName();
                    }
                    registered = true;
                }
            }
        }
        
        while(gameActive){
            
        }
        
        /* Creates an object of a class
        Connection connection = new Connection(0, 5);
        connection.setInitialDotPosition(0);
        connection.setFinalDotPosition(1);
       
        ClientController myControler = new ClientController();
        myControler.clientSend(connection);
        */
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