package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataPackages.ClassReference;
import dataPackages.DotConnectionPackage;
import dataPackages.RegisterPack;
import dataPackages.ToFigurePackage;
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
    }
    
    public void createConnection(DotConnectionPackage connection) throws Exception{
        int initalDotPosition = connection.getInitialDot();
        int finalDotPosition = connection.getFinalDot();
        LinkedList figureList = grid.createConnection(initalDotPosition, finalDotPosition);
        int score = figureList.getSize() * 2;
        ToFigurePackage figurePackage = new ToFigurePackage(figureList, 0, score);
        serverSend(figurePackage);
    }
    
    public void serverSend(Object object) throws Exception{
  
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
                    
                    if (reference.getReference().equals("DotConnectionPackage")){
                         
                    }
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
