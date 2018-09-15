package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import JSON.*;
 
public class BufferedSocketClient {
    
    public static void main(String[] args) throws Exception {
        Potatoe myPotatoe = new Potatoe();
        myPotatoe.setPrice(300);
        myPotatoe.setWeight(1);
        myPotatoe.setType("Bintje");
        
        String sendPotatoe = JSONUtil.convertJavaToJson(myPotatoe);

        Socket socket1 = new Socket(InetAddress.getLocalHost(), 1777);

        BufferedReader br = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

        PrintWriter pw = new PrintWriter(socket1.getOutputStream(), true);

        pw.println(sendPotatoe);
    
        while ((sendPotatoe = br.readLine()) != null) {
            System.out.println(sendPotatoe);
            pw.println("bye");

            if (sendPotatoe.equals("bye"))
                break;
        }
        
        br.close();
        pw.close();
        socket1.close();
    } 
}