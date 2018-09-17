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
    
    private static boolean registered = false;
    private static boolean gameActive = false;
    private static boolean showResults = false;
    
    /////
    private static boolean onTurn;
    
    
    public static void main (String[] args) throws Exception{
        
        menu = new MenuFrame();
        while(!registered){
            if(!(menu.getNickName().equals(""))){
                
                ClassReference reference = new ClassReference("registerPack");
                RegisterPack initialPackage = new RegisterPack(InetAddress.getLocalHost().getHostAddress(),menu.getNickName(),0);
                clientSend(initialPackage,reference);
                
                //Sockets recieved simulation
                playerNumber = 1; 
                p1Name = menu.getNickName();
                p2Name = "Pancho";
                //
                
                if(playerNumber == 0 ){
                    menu.standBy();
                }else{
                    registered = true;
                    gameActive = true;
                    menu.setVisible(false);
                }
            }
        }
        
        game = new MainFrame();
        grid = game.getGameFrame();
        info = game.getInfoFrame();
        Thread recievePackages = new Thread( new ClientController());
        
        while(gameActive){
            recievePackages.start();
            
            new Thread (new Runnable() {
            @Override
            public void run() {
                if(turnNumber % 2 == 0){
                    info.setP1Name(" "+p1Name);
                    info.setP2Name(">"+p2Name);
                }else{
                    info.setP1Name(">"+p1Name);
                    info.setP2Name(" "+p2Name);
                }
                info.setP1Score(p1Score);
                info.setP2Score(p2Score);
                info.setTurnNumber(turnNumber);
            }
            }).start();
                
                
               
                
                
                
              
        }
        
        if(showResults){
            
        }
        
    }
   
    public static void clientSend(Object object, Object classReference){
        try {
            Socket clientSocket = new  Socket(menu.getServerIp(), 9090);
            
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
                    
                    if (reference.getReference().equals("RegisterPack")){
                         RegisterPack recievedRegister = JSONUtil.convertJsonToJava(recievedObjectAsString, RegisterPack.class);
                         this.playerNumber = recievedRegister.getPlayerNumber();
                         
                         if(playerNumber==1){
                            p1Name = menu.getNickName();
                            p2Name = recievedRegister.getOtherPlayerName();
                        }else{
                            p1Name = recievedRegister.getOtherPlayerName();
                            p2Name = menu.getNickName();
                        }
                         break;
                    }
                    if (reference.getReference().equals("toFigurePackage")){
                        toFigurePackage recievedFigureList = JSONUtil.convertJsonToJava(recievedObjectAsString, toFigurePackage.class);
                        grid.generateFigure(recievedFigureList.getList());
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}