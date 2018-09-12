

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Servidor  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoServidor mimarco=new MarcoServidor();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}

class MarcoServidor extends JFrame implements Runnable{
	
	public MarcoServidor(){
		
		setBounds(1200,300,280,350);				
			
		JPanel milamina= new JPanel();
		
		milamina.setLayout(new BorderLayout());
		
		areatexto=new JTextArea();
		
		milamina.add(areatexto,BorderLayout.CENTER);
		
		add(milamina);
		
		setVisible(true);
		
		Thread mihilo = new Thread(this);
		
		mihilo.start();
		
		}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ServerSocket servidor = new ServerSocket(9999);
			
			String nick, ip, mensaje;
			
			PaqueteEnvio paqueteRecibido;
			
			
			while(true) {
			
				Socket inputSocket = servidor.accept();
			
				ObjectInputStream paquete_datos = new ObjectInputStream(inputSocket.getInputStream());
				paqueteRecibido = (PaqueteEnvio) paquete_datos.readObject();
				
				areatexto.append(paqueteRecibido.getMensaje());
				
				Socket outputSocket = new Socket(InetAddress.getLocalHost().getHostAddress(),9090);
				ObjectOutputStream flujoSalida = new ObjectOutputStream(outputSocket.getOutputStream());
				flujoSalida.writeObject(paqueteRecibido);
				
				outputSocket.close();
				inputSocket.close();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	private	JTextArea areatexto;
}
