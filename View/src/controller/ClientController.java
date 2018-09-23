package controller;

import com.sun.security.ntlm.Client;
import dataPackages.*;
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

/**
  * Player's game controller. Interacts with the game server and updates data to the player's display.
  */
public class ClientController implements Runnable{
    
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
    private static int turnNumber = 1;
    /**
     * User's menu.
     */
    private static MenuFrame menu;
    /**
     * User's game grid.
     */
    private static GameFrame grid; 
    /**
     * User's game grid.
     */
    private static InfoFrame info; 
    /**
     * User's main game grid.
     */
    private static MainFrame game;
    /**
     * User's results window.
     */
    private static ResultsFrame results;
    
    /**
     * Final game session data. 
     */
    private static DataPack finalPack;
    
    /**
     *  Indicates if the register stage is completed.
     */
    private static boolean registered = false;
    
    /**
     *  Indicates if the game stage is completed.
     */
    private static boolean gameActive = false;
    
    /**
     *  Indicates if the player's nickname has being sent to the server.
     */
    private static boolean nicknameSent = true;
    
    public static void main (String[] args) throws Exception{
        
        Thread recievePackages = new Thread(new ClientController());
        recievePackages.start(); 
        
        menu = new MenuFrame();
        while(!registered){
            try {Thread.sleep(10);
            }catch (Exception e){}
            
            if(!(menu.getNickName().equals(""))){
                
                if(nicknameSent){
                    ClassReference reference = new ClassReference("RegisterPack");
                    RegisterPack initialPackage = new RegisterPack(InetAddress.getLocalHost().getHostAddress(),menu.getNickName(),0);
                    clientSend(initialPackage,reference);
                    nicknameSent = false;
                }
                
                Thread.sleep(1000);
                
                if(playerNumber == 0 ){
                    menu.setText("You are in the server queue, waiting for turn...");
                }else{
                    gameActive = true;
                    menu.setVisible(false);
                    registered = true;
                }
            }
        }
        
        game = new MainFrame(playerNumber);
        grid = game.getGameFrame();
        info = game.getInfoFrame();
        
        while(gameActive){
             
            Thread.sleep(10);
            info.setTurnNumber(turnNumber);
            if(turnNumber % 2 != 0){
                info.setP1Name(">"+p1Name);
                info.setP2Name(" "+p2Name);
                if(playerNumber == 1){
                    grid.setOnTurn(true);
                }else{
                    grid.setOnTurn(false);
                }
            }else{
                info.setP1Name(" "+p1Name);
                info.setP2Name(">"+p2Name);
                if(playerNumber == 2){
                    grid.setOnTurn(true);
                }else{
                    grid.setOnTurn(false);
                }
            }
            
            Thread.sleep(10);    
            if(grid.getLinked()){
                ClassReference reference = new ClassReference("DotConnectionPack");
                DotConnectionPack initialPackage = new DotConnectionPack(grid.getFirstLinkDot(),grid.getSecondLinkDot(),playerNumber);
                clientSend(initialPackage,reference);
                grid.resetLinks();        
            }
            
            Thread.sleep(10);  
            if(finalPack != null){
                game.setVisible(false);
                gameActive = false;
            }
        }
        
        
        results = new ResultsFrame(finalPack.getWinner(), p1Name, p2Name, Integer.toString(finalPack.getScore1()), Integer.toString(finalPack.getScore2()), Integer.toString(turnNumber));
        
    }
    
    /**
     * Converts a concatenated string into a linked list of integers.
     * @param strFigure Received strings, contains a series of integers.
     */
    public static LinkedList<Integer> stringToFigure(String strFigure){
        LinkedList<Integer> figure = new LinkedList<>();
        String[] strPositions = strFigure.split("\\.");
        for(String pos: strPositions)
            figure.add(Integer.valueOf(pos), Integer.valueOf(pos));
        return figure;
    }
    
    /**
     * Sent data to the server through sockets and in a JSON format. 
     * @param object Object that contains the data that will be sent.
     * @param classReference Identifier of the class sent.
     */
    public static void clientSend(Object object, Object classReference){
        try {
            //"192.168.100.14"
            Socket clientSocket = new  Socket("192.168.100.14", 9090);
            
            String sendObject = JSONUtil.convertJavaToJson(object);
            String sendClassReference = JSONUtil.convertJavaToJson(classReference);
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            out.println(sendObject);
            out.println(sendClassReference);
            
            System.out.println("Message was sent from the client");
            
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

    /**
     * This method listens to incoming messages from the server.
     */
    @Override
    public void run() {
        try {
            int portNumber = 9099;
            ServerSocket clientAsServer = new ServerSocket(portNumber);
            
            System.out.println("Client waiting for a connection on port: " + portNumber);
            
            while (true){
                Socket fromClientSocket = clientAsServer.accept();
                              
                BufferedReader in = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));
                
                String recievedObjectAsString = in.readLine();
                String recievedClassReferenceAsString = in.readLine();
                
                in.close();
                fromClientSocket.close();
                
                while (recievedObjectAsString != null && recievedClassReferenceAsString != null){
                    ClassReference reference = JSONUtil.convertJsonToJava(recievedClassReferenceAsString, ClassReference.class);

                    if (reference.getReference().equals("RegisterPack")){
                        System.out.println("Client recieved a server response: RegisterPack");
                        RegisterPack register = JSONUtil.convertJsonToJava(recievedObjectAsString, RegisterPack.class);
                        this.playerNumber = register.getPlayerNumber();
                         
                        if(playerNumber==1){
                            p1Name = menu.getNickName();
                            p2Name = register.getOtherPlayerName();
                        }else{
                            p1Name = register.getOtherPlayerName();
                            p2Name = menu.getNickName();
                        }
                        break;
                    }
                    
                    if (reference.getReference().equals("ToFigurePack")){
                        System.out.println("Client recieved a server response: ToFigurePack");
                        ToFigurePack figureList = JSONUtil.convertJsonToJava(recievedObjectAsString, ToFigurePack.class);
                        LinkedList<Integer> figure = stringToFigure(figureList.getFigure());
                        grid.generateFigure(figure,figureList.getPlayerNumber());
                        break;
                    }
                    
                    if (reference.getReference().equals("DotConnectionPack")){
                        System.out.println("Client recieved a server response: DotConnectionPack");
                        DotConnectionPack dotLink = JSONUtil.convertJsonToJava(recievedObjectAsString, DotConnectionPack.class);
                        grid.linkDots(dotLink.getInitialDot(),dotLink.getFinalDot(),2);
                        break;
                    }
                    
                    if (reference.getReference().equals("DataPack")){
                        System.out.println("Client recieved a server response: DataPack");
                        DataPack data = JSONUtil.convertJsonToJava(recievedObjectAsString, DataPack.class);
                        
                        if(data.getWinner() == null){
                            turnNumber++;
                            info.setP1Score(data.getScore1());
                            info.setP2Score(data.getScore2());
                        }else{
                            finalPack = data;
                        }
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}