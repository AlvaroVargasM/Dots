package controller;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erick Barrantes
 */
public class ServerController implements Runnable{
    
    private static Grid grid;
    final ObjectMapper objectMapper = new ObjectMapper();
    private static Queue playerQueue;
    private Player player1;
    private Player player2;
    private static int turnNumber;
    private static int maxTurnNumber;
    
    /**
     * Main method for serverController. Initializes all attributes and starts thread 
     * to listen for connections.
     * @param args
     * @throws Exception
     */
    public static void main (String[] args) throws Exception{
        Thread recievePackages = new Thread(new ServerController());
        recievePackages.start();
        grid = new Grid(5, 5);
        playerQueue = new Queue();
        turnNumber = 1;
        maxTurnNumber = 16;
    }
    
    /**
     * After receiving a DotConnectionPack from a client, this method creates the
     * received connection in the grid, creates a string figure and send a 
     * ToFigurePack if a figure was created in the grid, sends the DotConnectionPack 
     * to the other player and finally, sends the DataPack with all the data updated.
     * 
     * @param connection
     * @throws Exception
     */
    public void createConnection(DotConnectionPack connection) throws Exception{
        int initalDotPosition = connection.getInitialDot();
        int finalDotPosition = connection.getFinalDot();
        if(initalDotPosition != 0 && finalDotPosition != 0){
            LinkedList figureList = grid.createConnection(initalDotPosition, finalDotPosition);
            String strFigure = figureToString(figureList);
            Player player = null;

            if(connection.getPlayerNumber() == 1){
                player = player1;
                sendDotConnectionPack(connection, player2.getPlayerIp());
            }
            else{
                player = player2;
                sendDotConnectionPack(connection, player1.getPlayerIp());
            }

            int score = 0;
            if(!strFigure.equals("")){
                score = figureList.getSize() * 2;
                sendToFigurePack(strFigure, player.getPlayerNumber());
            }

            player.setScore(player.getScore() + score);

            sendDataPacks(getWinnerName());
        }
    }
    
    /**
     * Receives a LinkedList representing a figure and it converts it to a String
     * @param figure
     * @return
     */
    public String figureToString(LinkedList figure){
        String strFigure = "";
        if(figure != null){
            for(LinkedListNode node = figure.getFirstNode(); node != null; 
                node = node.getNextNode()){
                strFigure += node.getPosition() + ".";
            }
        }return strFigure;
    }
    
    /**
     * If a new figure was created, send a ToFigurePack to both players.
     * @param strFigure
     * @param playerNumber
     */
    public void sendToFigurePack(String strFigure, int playerNumber){
        ToFigurePack figurePack = new ToFigurePack(strFigure, playerNumber);
        ClassReference classReference = new ClassReference("ToFigurePack");
        serverSend(figurePack, classReference, player1.getPlayerIp());
        serverSend(figurePack, classReference, player2.getPlayerIp());
    }
    
    /**
     * Sends a DotConnectionPack to the IP received as a parameter.
     * @param dotConnectionPack
     * @param ip
     */
    public void sendDotConnectionPack(DotConnectionPack dotConnectionPack, String ip){
        ClassReference classReference = new ClassReference("DotConnectionPack");
        serverSend(dotConnectionPack, classReference, ip);
    }
    
    /**
     * Updates all the data needed for the clients and sends DataPacks to both players.
     * Sends the name of the winner if the maxTurnNumber is reached.
     * @param winnerName
     */
    public void sendDataPacks(String winnerName){
        ClassReference classReference = new ClassReference("DataPack");
        int score1 = player1.getScore();
        int score2 = player2.getScore();
        String ip1 = player1.getPlayerIp();
        String ip2 = player2.getPlayerIp();
        
        DataPack packPlayer1 = new DataPack(winnerName, score1, score2, 1);
        serverSend(packPlayer1, classReference, ip1);
        
        DataPack packPlayer2 = new DataPack(winnerName, score1, score2, 2);
        serverSend(packPlayer2, classReference, ip2);
        
        turnNumber++;
        if (winnerName != null) resetData();
    }
    
    /**
     * When a RegisterPack arrives, a new player is added to the player Queue with
     * the information received and also checks if there are two players available 
     * to start a new match.
     * @param registerPack
     */
    public void registerNewPlayer(RegisterPack registerPack){
        String playerName = registerPack.getPlayerName();
        String playerIp = registerPack.getPlayerIp();
        if(!playerQueue.contains(playerIp)){
            int playerNumber = registerPack.getPlayerNumber();

            Player player = new Player(playerName, playerIp, playerNumber);
            playerQueue.enqueue(player);

            if(playerQueue.getSize() == 2 && player1 == null && player2 == null){
                startNewMatch();
            }
        }
    }
    
    /**
     * Dequeues the first two players in the Queue, sends registerPacks to both of them
     * and this starts a new match.
     */
    public void startNewMatch(){
        player1 = playerQueue.dequeue().getPlayer();
        player1.setPlayerNumber(1);
        player2 = playerQueue.dequeue().getPlayer();
        player2.setPlayerNumber(2);
        sendRegisterPack();
    }
    
    /**
     * Gets the data needed to send RegisterPacks to both players.
     */
    public void sendRegisterPack(){
        ClassReference classReference = new ClassReference("RegisterPack");
        
        String player1Name = player1.getNickname();
        String player2Name = player2.getNickname();
        String player1Ip = player1.getPlayerIp();
        RegisterPack registerPackPlayer1 = new RegisterPack(player1Ip, player1Name, 1);
        registerPackPlayer1.setOtherPlayerName(player2Name);
        serverSend(registerPackPlayer1, classReference, player1Ip);
        
        String player2Ip = player2.getPlayerIp();
        RegisterPack registerPackPlayer2 = new RegisterPack(player2Ip, player2Name, 2);
        registerPackPlayer2.setOtherPlayerName(player1Name);
        serverSend(registerPackPlayer2, classReference, player2Ip);
    }
    
    /**
     * 
     * @return
     */
    public String getWinnerName(){
        String winner = null;
        if(turnNumber == maxTurnNumber){
            if(player1.getScore() > player2.getScore()) winner = player1.getNickname();
            else if(player1.getScore() < player2.getScore()) winner = player2.getNickname();
            else winner = "Draw";
        }return winner;
    }
    
    public void resetData(){
        turnNumber = 1;
        grid = new Grid(5, 5);
        player1 = null;
        player2 = null;
    }
    
    public void playerDisconnected(DataPack receivedDataPack){
        String winner = receivedDataPack.getWinner();
        sendDataPacks(winner);
    }
    /**
     * Sent data to the clients through sockets and in a JSON format. 
     * @param object1 Object that contains the data that will be sent.
     * @param object2 Identifier of the class sent.
     * @param ipAddress IP address of the client.
     */
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
    
    /**
     * This method listens to incoming messages from the clients.
     */
    @Override
    public void run() {
        try {
            int portNumber = 9090;
            ServerSocket server = new ServerSocket(portNumber);
            
            System.out.println("Server waiting for a connection on port: " + portNumber);
            
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
                    
                    if(reference.getReference().equals("DataPack")){
                        System.out.println("Data pack arrived");
                        DataPack receivedDataPack = JSONUtil.convertJsonToJava(recievedObjectAsString, DataPack.class);                        
                        playerDisconnected(receivedDataPack);
                        break;
                    }
                }  
            }
        } catch (Exception ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}