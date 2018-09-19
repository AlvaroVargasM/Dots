package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataPackages.ClassReference;
import dataPackages.DotConnectionPack;
import dataPackages.RegisterPack;
import dataPackages.ToFigurePack;
import gameLogic.LinkedList;
import gameLogic.Grid;
import jsonLogic.JSONUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController implements Runnable{
    
    private static Grid grid;
    final ObjectMapper objectMapper = new ObjectMapper();

    
    public static void main (String[] args) throws Exception{
        grid = Grid.getGrid(5, 5);
        Thread recievePackages = new Thread(new ServerController());
        recievePackages.start();
        System.out.println(grid.toString());
    }
    
    public void createConnection(DotConnectionPack connection) throws Exception{
        int initalDotPosition = connection.getInitialDot();
        int finalDotPosition = connection.getFinalDot();
        LinkedList figureList = grid.createConnection(initalDotPosition, finalDotPosition);
        int score = figureList.getSize() * 2;
        ToFigurePack figurePackage = new ToFigurePack(figureList);
        ClassReference classReference = new ClassReference("ToFigurePackage");
        serverSend(figurePackage, classReference);
    }
    
    public void serverSend(Object object1, Object object2) throws Exception{
  
        // Converts the object into a JSON String
        String sendObject1 = JSONUtil.convertJavaToJson(object1);
        String sendObject2 = JSONUtil.convertJavaToJson(object2);

        // Opens a socket
        Socket serverSocket = new Socket(InetAddress.getLocalHost(), 1777);

        // Creates 
        BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
        out.println(sendObject1);
        out.println(sendObject2);
        
        in.close();
        out.close();
        serverSocket.close();
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
                }
            }
        } catch (Exception ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}