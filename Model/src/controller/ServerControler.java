package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataPackages.DotConnectionPackage;
import gameLogic.LinkedList;
import gameLogic.Grid;
import jsonLogic.JSONUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerControler {
    
    private static Grid grid;
    final ObjectMapper objectMapper = new ObjectMapper();

    
    public static void main (String[] args) throws Exception{
        grid = Grid.getGrid(5, 5);
        ServerControler myControler = new ServerControler();
        myControler.serverReceive();
    }
    
    public void createConnection(DotConnectionPackage connection) throws Exception{
        System.out.println("ENTROOOOOOOOO");
        int initalDotPosition = connection.getInitialDot();
        int finalDotPosition = connection.getFinalDot();
        System.out.println(connection.getInitialDot() + connection.getFinalDot());
        LinkedList figure = grid.createConnection(initalDotPosition, finalDotPosition);
        
        serverSend(figure);
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
    
    public void serverReceive() throws Exception{
        int cTosPortNumber = 1777;
        String receivedString;

        // Creates a socket an
        ServerSocket servSocket = new ServerSocket(cTosPortNumber);
        System.out.println("Waiting for a connection on " + cTosPortNumber);

        Socket fromClientSocket = servSocket.accept();
        
        PrintWriter out = new PrintWriter(fromClientSocket.getOutputStream(), true);

        BufferedReader in = new BufferedReader(new InputStreamReader(fromClientSocket.getInputStream()));

        while ((receivedString = in.readLine()) != null) {
            DotConnectionPackage recievedObject = JSONUtil.convertJsonToJava(receivedString, DotConnectionPackage.class);
            createConnection(recievedObject);
//            recievedString = "Server returns " + recievedObject.getPrice()+ " " + recievedObject.getWeight();
//            out.println(recievedString);
//            JsonNode jsonNode = objectMapper.readTree(receivedString);
//            String classType = jsonNode.get("@type").asText();
//            if(classType ==` "Connection"){
//                Connection receivedObject = JSONUtil.convertJsonToJava(receivedString, Connection.class);
//                createConnection(receivedObject);
//            }
//            System.out.println("The message: " + recievedString);
//
//            if (recievedString.equals("bye")) {
//                out.println("bye");
//                break;
//            } 
//            else {
//                Potatoe recievedObject = JSONUtil.convertJsonToJava(recievedString, Potatoe.class);
//                recievedString = "Server returns " + recievedObject.getPrice()+ " " + recievedObject.getWeight();
//                out.println(recievedString);
//            }

        }
    out.close();
    in.close();

    fromClientSocket.close();
    }
}
