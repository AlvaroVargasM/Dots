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
import gameLogic.LinkedListNode;
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
        Thread recievePackages = new Thread(new ServerController());
        recievePackages.start();
        grid = Grid.getGrid(5, 5);
        playerQueue = new Queue();
        
    }
    
    public void createConnection(DotConnectionPack connection) throws Exception{
        int initalDotPosition = connection.getInitialDot();
        int finalDotPosition = connection.getFinalDot();
        LinkedList figureList = grid.createConnection(initalDotPosition, finalDotPosition);
        String strFigure = figureToString(figureList);
        Player player = null;
        if(connection.getPlayerNumber() == 1) player = player1;
        else player = player2;
        
        int score = 0;
        String ip = player.getPlayerIp();
        if(strFigure != ""){
            score = figureList.getSize() * 2;
            sendToFigurePack(strFigure, ip);
        }

        player.setScore(player.getScore() + score);
        
        sendDataPack(null);
    }
    
    public String figureToString(LinkedList figure){
        String strFigure = "";
        if(figure != null){
            for(LinkedListNode node = figure.getFirstNode(); node != null; 
                node = node.getNextNode()){
                strFigure += node.getPosition() + ".";
            }
        }return strFigure;
    }
    
    public void sendToFigurePack(String strFigure, String ip){
        ToFigurePack figurePack = new ToFigurePack(strFigure);
        ClassReference classReference = new ClassReference("ToFigurePack");
        serverSend(figurePack, classReference, player1.getPlayerIp());
    }
    
    public void sendDataPack(String winner){
        ClassReference classReference = new ClassReference("DataPack"); //crea class reference, manda huevo
        int score1 = player1.getScore();
        int score2 = player2.getScore();
        
        DataPack packPlayer1 = new DataPack(winner, score1, score2, 1);
        serverSend(packPlayer1, classReference, player1.getPlayerIp());
        
        DataPack packPlayer2 = new DataPack(winner, score1, score2, 2);
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
        player1 = playerQueue.dequeue().getPlayer();
        player2 = playerQueue.dequeue().getPlayer();
        sendRegisterPack();
    }
    
    public void sendRegisterPack(){
        ClassReference classReference = new ClassReference("RegisterPack");
        
        String player1Name = player1.getNickname();
        String player2Name = player2.getNickname();
        String player1Ip = player1.getPlayerIp();
        RegisterPack registerPackPlayer1 = new RegisterPack(player1Ip, player1Name, 1, player2Name);
        serverSend(registerPackPlayer1, classReference, player1Ip);
        
        String player2Ip = player2.getPlayerIp();
        RegisterPack registerPackPlayer2 = new RegisterPack(player2Ip, player2Name, 2, player1Name);
        serverSend(registerPackPlayer2, classReference, player2Ip);
    }
    
    public void serverSend(Object object1, Object object2, String ipAddress){
  
        try {
            Socket serverSocket = new Socket(ipAddress, 9099);
            
            String sendObject1 = JSONUtil.convertJavaToJson(object1);
            String sendObject2 = JSONUtil.convertJavaToJson(object2);                       
            
            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
            
            out.println(sendObject1);
            out.println(sendObject2);
            
            System.out.println("Message was sent from the server");
           
            out.close();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            int cTosPortNumber = 9090;
            ServerSocket server = new ServerSocket(cTosPortNumber);
            
            System.out.println("Server waiting for a connection on port: " + cTosPortNumber);
            
            while (true){
                Socket fromServerSocket = server.accept();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(fromServerSocket.getInputStream()));
                
                String recievedObjectAsString = in.readLine();
                String recievedClassReferenceAsString = in.readLine();
                
                in.close();
                fromServerSocket.close();
                
                while (recievedObjectAsString != null && recievedClassReferenceAsString != null){
                    ClassReference reference = JSONUtil.convertJsonToJava(recievedClassReferenceAsString, ClassReference.class);
                    if (reference.getReference().equals("DotConnectionPack")){
                        System.out.println("Dot connection pack pack arrived");
                        DotConnectionPack recievedDotConnection = JSONUtil.convertJsonToJava(recievedObjectAsString, DotConnectionPack.class);                        
                        createConnection(recievedDotConnection);
                        break;
                    }
                    if(reference.getReference().equals("RegisterPack")){
                        System.out.println("Register pack arrived");
                        RegisterPack receivedRegisterPack = JSONUtil.convertJsonToJava(recievedObjectAsString, RegisterPack.class);                        
                        registerNewPlayer(receivedRegisterPack);
                        break;
                    }
                }  
            }
        } catch (Exception ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}