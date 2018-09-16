package controller;

import JSON
import visualFrames.*;
import jsonLogic.JSONUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


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
   
    public void clientSend(Object object) throws Exception{
  
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

//            if (recievedString.equals("bye")) {
//                out.println("bye");
//                break;
//            } 
//            else {
//                Potatoe recievedObject = JSONUtil.convertJsonToJava(recievedString, Potatoe.class);
//                recievedString = "Server returns " + recievedObject.getPrice()+ " " + recievedObject.getWeight();
//                out.println(recievedString);
//            }
        }
    out.close();
    in.close();

    fromClientSocket.close();
    }   
}