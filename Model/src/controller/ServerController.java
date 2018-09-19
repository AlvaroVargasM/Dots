package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataPackages.ClassReference;
import dataPackages.DataPack;
import dataPackages.DotConnectionPack;
import dataPackages.RegisterPack;
import dataPackages.ToFigurePack;
import gameLogic.LinkedList;
import gameLogic.Grid;
import gameLogic.Player;
import gameLogic.Queue;
import jsonLogic.JSONUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerController implements Runnable{
    
    private static Grid grid;
    final ObjectMapper objectMapper = new ObjectMapper();
    private static Queue playerQueue;
    private Player player1;
    private Player player2;
    

    
    public static void main (String[] args) throws Exception{
        grid = Grid.getGrid(5, 5);
        playerQueue = new Queue();
        Thread recievePackages = new Thread(new ServerController());
        recievePackages.start();
    }
    
    public void createConnection(DotConnectionPack connection) throws Exception{
        int initalDotPosition = connection.getInitialDot();
        int finalDotPosition = connection.getFinalDot();
        LinkedList figureList = grid.createConnection(initalDotPosition, finalDotPosition);
        int score = figureList.getSize() * 2;
        String ip;
        if(connection.getPlayerNumber() == 1){
            ip = player1.getPlayerIp();
            player1.setScore(player1.getScore() + score);
        }
        else{
            ip = player2.getPlayerIp();
            player2.setScore(player2.getScore() + score);
        }
        if(figureList != null) sendToFigurePack(figureList, ip);
        sendDataPack(null);
    }
    
    public void sendToFigurePack(LinkedList figureList, String ip){
        ToFigurePack figurePackage = new ToFigurePack(figureList);
        ClassReference classReference = new ClassReference("ToFigurePackage");
        serverSend(figurePackage, classReference, ip);
    }
    
    public void sendDataPack(String winner){
        int score1 = player1.getScore();
        int score2 = player2.getScore();
        DataPack packPlayer1 = new DataPack(winner, score1, score2, 1);
        DataPack packPlayer2 = new DataPack(winner, score1, score2, 2);
        ClassReference classReference = new ClassReference("DataPack");
        serverSend(packPlayer1, classReference, player1.getPlayerIp());
        serverSend(packPlayer2, classReference, player2.getPlayerIp());
    }
    
    public void registerNewPlayer(RegisterPack registerPack){
        String playerName = registerPack.getPlayerName();
        String playerIp = registerPack.getPlayerIp();
        int playerNumber = registerPack.getPlayerNumber();
        Player player = new Player(playerName, playerIp, playerNumber);
        playerQueue.enqueue(player);
        if(playerQueue.getSize() == 2 && player1 == null && player2 == null){
            startNewMatch();
        }
    }
    
    public void startNewMatch(){
//        player1 = playerQueue.dequeue().getPlayer();
//        player2 = playerQueue.dequeue().getPlayer();
    }
    
    public void serverSend(Object object1, Object object2, String ipAddress){
  
        try {
            // Converts the object into a JSON String
            String sendObject1 = JSONUtil.convertJavaToJson(object1);
            String sendObject2 = JSONUtil.convertJavaToJson(object2);
            
            // Opens a socket
            Socket serverSocket = new Socket(ipAddress, 1777);
            
            // Creates
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            out.println(sendObject1);
            out.println(sendObject2);
            
            in.close();
            out.close();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void run() {
       try {
            int cTosPortNumber = 9090;
            
            System.out.println("Waiting for a connection on " + cTosPortNumber);
            
            while (true){
                ServerSocket server = new ServerSocket(cTosPortNumber);
                Socket fromServerSocket = server.accept();
                
                PrintWriter out = new PrintWriter(fromServerSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(fromServerSocket.getInputStream()));
                
                String recievedObjectAsString = in.readLine();
                String recievedClassReferenceAsString = in.readLine();
                
                out.close();
                in.close();
                fromServerSocket.close();
                
                while (recievedObjectAsString != null && recievedClassReferenceAsString != null){
                    ClassReference reference = JSONUtil.convertJsonToJava(recievedClassReferenceAsString, ClassReference.class);
                    if (reference.getReference().equals("DotConnectionPack")){
                        DotConnectionPack recievedDotConnection = JSONUtil.convertJsonToJava(recievedObjectAsString, DotConnectionPack.class);                        
                        createConnection(recievedDotConnection);
                    }
                    if(reference.getReference().equals("RegisterPack")){
                        RegisterPack receivedRegisterPack = JSONUtil.convertJsonToJava(recievedObjectAsString, RegisterPack.class);                        
                        registerNewPlayer(receivedRegisterPack);
                    }
                }
            }
        } catch (Exception ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}