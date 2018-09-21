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
    
    private static DataPack finalPack;
    
    private static boolean registered = false;
    private static boolean gameActive = false;
    
    public static void main (String[] args) throws Exception{
        
        menu = new MenuFrame();
        while(!registered){
            try {Thread.sleep(10);}
            catch (Exception e)
                {e.printStackTrace();}
            
            if(!(menu.getNickName().equals(""))){
                
                ClassReference reference = new ClassReference("RegisterPack");
                RegisterPack initialPackage = new RegisterPack(InetAddress.getLocalHost().getHostAddress(),menu.getNickName(),0);
                clientSend(initialPackage,reference);
                
                
                /*Sockets recieved simulation
                playerNumber = 1; 
                p1Name = menu.getNickName();
                p2Name = "Pancho";
                //*/
                
                if(playerNumber == 0 ){
                    menu.standBy();
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
        Thread recievePackages = new Thread(new ClientController());
        recievePackages.start(); 
        
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
                DotConnectionPack initialPackage = new DotConnectionPack(grid.getFirstLinkDot(),grid.getFirstLinkDot(),playerNumber);
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
     *
     * @param object
     * @param classReference
     */
    public static void clientSend(Object object, Object classReference){
        try {
            //menu.getServerIp()
            //192.168.0.121
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
    
    /**
     * Increase the game session's turn number by one.
     */

    @Override
    public void run() {
       try {
            int cTosPortNumber = 9099;
            ServerSocket clientAsServer = new ServerSocket(cTosPortNumber);
            
            while (true){
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
                        System.out.println("Client recieved a server response");
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
                    
                    if (reference.getReference().equals("toFigurePack")){
                        System.out.println("Client recieved a server response");
                        ToFigurePack figureList = JSONUtil.convertJsonToJava(recievedObjectAsString, ToFigurePack.class);
                        grid.generateFigure(figureList.getList());
                        break;
                    }
                    
                    if (reference.getReference().equals("dataPack")){
                        System.out.println("Client recieved a server response");
                        DataPack data = JSONUtil.convertJsonToJava(recievedObjectAsString, DataPack.class);
                        
                        if(data.getWinner() == null){
                            turnNumber++;
                            p1Score = data.getScore1();
                            p2Score = data.getScore2();
                        }else{
                            finalPack = data;
                        }
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}