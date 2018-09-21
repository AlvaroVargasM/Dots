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
        Thread recievePackages = new Thread(new ServerController());
        recievePackages.start();
        grid = Grid.getGrid(5, 5);
        playerQueue = new Queue();
        
    }
    
    public void createConnection(DotConnectionPack connection) throws Exception{
        //Obtiene info de DotConnectionPack recibido, esta funcion aun no importa mucho porque la
        //esta picha de programa aun no nos sirve
        int initalDotPosition = connection.getInitialDot();
        int finalDotPosition = connection.getFinalDot();
        LinkedList figureList = grid.createConnection(initalDotPosition, finalDotPosition);
        
        int score = figureList.getSize() * 2;
        String ip;
        
        //Si la conexion la hizo el jugador 1
        if(connection.getPlayerNumber() == 1){
            ip = player1.getPlayerIp(); //Obtiene ip del J1
            player1.setScore(player1.getScore() + score); //Le agrega el score de figura creada
        }
        //Si la hizo el jugador 2
        else{
            ip = player2.getPlayerIp();
            player2.setScore(player2.getScore() + score);
        }
        //Si se creo una figura
        if(figureList != null) sendToFigurePack(figureList, ip); //Manda paquete de figura
        
        sendDataPack(null); //Manda DataPack para cambiar de turno en cliente (creo, eso me dijo Mariano)
    }
    
    public void sendToFigurePack(LinkedList figureList, String ip){
        //Esto solo crea un toFigurePack y lo envia
        ToFigurePack figurePackage = new ToFigurePack(figureList);
        ClassReference classReference = new ClassReference("ToFigurePackage");
        serverSend(figurePackage, classReference, ip);
    }
    
    public void sendDataPack(String winner){
        ClassReference classReference = new ClassReference("DataPack"); //crea class reference, manda huevo
        
        //Obtiene score de ambos jugadores
        int score1 = player1.getScore();
        int score2 = player2.getScore();
        
        //Crea DataPack para J1 y lo envia
        DataPack packPlayer1 = new DataPack(winner, score1, score2, 1);
        serverSend(packPlayer1, classReference, player1.getPlayerIp());
        
        //Crea DataPack para J2 y lo envia
        DataPack packPlayer2 = new DataPack(winner, score1, score2, 2);
        serverSend(packPlayer2, classReference, player2.getPlayerIp());
    }
    
    public void registerNewPlayer(RegisterPack registerPack){
        //En esta funcion pasa la magia inicial perro
       
        //Se obtiene info del RegisterPack 
        String playerName = registerPack.getPlayerName();
        String playerIp = registerPack.getPlayerIp();
        int playerNumber = registerPack.getPlayerNumber();
        
        //Crea un nuevo jugador y lo mete en la cola
        Player player = new Player(playerName, playerIp, playerNumber);
        playerQueue.enqueue(player);
        
        startNewMatch();
        
        //Supuestamente deberia de ser asi, esperar a que hayan dos compitas en la
        //cola para poder empezar, pero por el momento se ignora
        //if(playerQueue.getSize() == 2 && player1 == null && player2 == null){
            //startNewMatch();
        //}
    }
    
    public void startNewMatch(){
        //Se obtienen los dos jugadores (solo uno por el momento) de la cola y 
        //se envian los RegisterPack
        player1 = playerQueue.dequeue().getPlayer();
        //player2 = playerQueue.dequeue().getPlayer();
        sendRegisterPack();
    }
    
    public void sendRegisterPack(){
        ClassReference classReference = new ClassReference("RegisterPack");
        
        //De los jugadores se obtiene la info que ocupa el RegisterPack y se envian
        //los RegisterPack de ambos maes. Ahorita solo con uno.
        String player1Name = player1.getNickname();
        String player1Ip = player1.getPlayerIp();
        RegisterPack registerPackPlayer1 = new RegisterPack(player1Ip, player1Name, 1, "");
        serverSend(registerPackPlayer1, classReference, player1Ip);
        
        //ESTO AUN NO PORQUE ESTAMOS MAMANDO PINGA
        //String player2Name = player2.getNickname();
        //String player2Ip = player2.getPlayerIp();
        //RegisterPack registerPackPlayer2 = new RegisterPack(player2Ip, player2Name, 2, player1Name);
        //serverSend(registerPackPlayer2, classReference, player2Ip);
        
    }
    
    //Comentarios miedo
    
    public void serverSend(Object object1, Object object2, String ipAddress){
  
        try {
            // Converts the object into a JSON String
            String sendObject1 = JSONUtil.convertJavaToJson(object1);
            String sendObject2 = JSONUtil.convertJavaToJson(object2);
            
            // Opens a socket
            Socket serverSocket = new Socket(ipAddress, 9099);
            
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
    @Override
    public void run() {
       try {
            int cTosPortNumber = 9090;
            
            ServerSocket server = new ServerSocket(cTosPortNumber);
            
            System.out.println("Server waiting for a connection on port: " + cTosPortNumber);
            
            while (true){
                Socket fromServerSocket = server.accept();
                
                PrintWriter out = new PrintWriter(fromServerSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(fromServerSocket.getInputStream()));
                
                String recievedObjectAsString = in.readLine();
                String recievedClassReferenceAsString = in.readLine();
                
                while (recievedObjectAsString != null && recievedClassReferenceAsString != null){
                    ClassReference reference = JSONUtil.convertJsonToJava(recievedClassReferenceAsString, ClassReference.class);
                    if (reference.getReference().equals("DotConnectionPack")){
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
                
                out.close();
                in.close();
                fromServerSocket.close();
            }
        } catch (Exception ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}