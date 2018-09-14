

import javax.swing.*;

import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoCliente mimarco=new MarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(600,300,280,350);
				
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
		}	
	
}

class LaminaMarcoCliente extends JPanel implements Runnable{
	
	@Override
	public void run() {
		
		try {//aqui es la parte donde se esta siempre a la escucha
			
			ServerSocket servidorCliente = new ServerSocket(9090);
			Socket cliente;
			PaqueteEnvio paqueteRecibido;
			
			while(true) {
				cliente = servidorCliente.accept();
				ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
				paqueteRecibido = (PaqueteEnvio) flujoEntrada.readObject();
				
				campochat.append("\n"+paqueteRecibido.getMensaje());
			}
			
		}catch(Exception e) {
			
		}
	}
	
	public LaminaMarcoCliente(){
		
		nick=new JTextField(5);
		add(nick);
		
		
		JLabel texto=new JLabel("-Chat-");
		add(texto);
		
		ip=new JTextField(8);	
		add(ip);
		
		campochat = new JTextArea(12,20);
		add(campochat);
	
		campo1=new JTextField(20);
		add(campo1);		

		miboton=new JButton("Enviar");
		
		EnviaTexto mievento = new EnviaTexto();
		miboton.addActionListener(mievento);
		
		add(miboton);	
		
		Thread escuchar = new Thread(this);
		escuchar.start();
	}
	
	private class EnviaTexto implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			try {
				ipAdress = InetAddress.getLocalHost().getHostAddress();
				
				Socket clientSocket = new Socket(ipAdress,9999);
				
				PaqueteEnvio datos = new PaqueteEnvio();
				
				datos.setIp(ip.getText());
				datos.setNick(nick.getText());
				datos.setMensaje(campo1.getText());
				
				ObjectOutputStream flujoSalida = new ObjectOutputStream(clientSocket.getOutputStream());
				
				flujoSalida.writeObject(datos);
				
				clientSocket.close();
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} 
		}	
	}	
		
		
	private JTextField campo1;
	
	private JTextField nick;
	
	private JTextField ip;
	
	private JTextArea campochat;
	
	private JButton miboton;
	
	private String ipAdress;
	
}