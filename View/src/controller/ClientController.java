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
        
        Thread recievePackages = new Thread(new ClientController());
        recievePackages.start(); 
        
        menu = new MenuFrame();
        while(!registered){
            try {Thread.sleep(10);}
            catch (Exception e)
                {e.printStackTrace();}
            
            if(!(menu.getNickName().equals(""))){
                
                ClassReference reference = new ClassReference("RegisterPack");
                RegisterPack initialPackage = new RegisterPack(InetAddress.getLocalHost().getHostAddress(),menu.getNickName(),0);
                clientSend(initialPackage,reference);
                
                Thread.sleep(1000); 
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
        //Thread recievePackages = new Thread(new ClientController());
        //recievePackages.start(); 
        
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
    
    public static LinkedList<Integer> stringToFigure(String strFigure){
        LinkedList<Integer> figure = new LinkedList<>();
        String[] strPositions = strFigure.split("\\.");
        for(String pos: strPositions)
            figure.add(Integer.valueOf(pos), Integer.valueOf(pos));
        return figure;
    }
    
    /**
     *
     * @param object
     * @param classReference
     */
    public static void clientSend(Object object, Object classReference){
        try {
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
                        grid.generateFigure(figure);
                        break;
                    }
                    
                    if (reference.getReference().equals("DotConnectionPack")){
                        System.out.println("Client recieved a server response: DotConnectionPack");
                        DotConnectionPack dotLink = JSONUtil.convertJsonToJava(recievedObjectAsString, DotConnectionPack.class);
                        grid.linkDots(dotLink.getInitialDot(),dotLink.getFinalDot());
                        break;
                    }
                    
                    if (reference.getReference().equals("DataPack")){
                        System.out.println("Client recieved a server response: DataPack");
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