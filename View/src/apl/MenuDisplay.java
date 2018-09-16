package apl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * Main meu of the application.
 */
public class MenuDisplay extends JFrame{
    
    public static void main(String[] args) {
		MenuDisplay marco1 = new MenuDisplay();
    }
    
    /**
     * Nick name of the player.
     */
    String nickName;
    
    /**
     * Main meu of the application.
     */
    public MenuDisplay (){
        nickName = "";
        
        this.setTitle("Dots"); 
        this.setSize(1000,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        JLabel title = new JLabel("Dots",SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(1000, 200));
        title.setFont(new Font("Tahoma", Font.BOLD, 53));
        title.setOpaque(true);
        title.setForeground(new Color(255, 255, 255));
        title.setBackground(new Color(255, 102, 0));
        this.getContentPane().add(title, BorderLayout.NORTH);
        add(title, BorderLayout.NORTH);
        
        JButton startButton = new JButton("Empezar partida");
        startButton.setPreferredSize(new Dimension(1000, 300));
        startButton.setFont(new Font("Tahoma", Font.BOLD, 35));
        startButton.setOpaque(true);
        startButton.setForeground(new Color(21, 72, 144));
        startButton.setBackground(Color.WHITE);
        startButton.setFocusPainted(false);
        this.getContentPane().add(startButton, BorderLayout.CENTER);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                nickName = JOptionPane.showInputDialog(MenuDisplay.this, "Enter your nick name: ", "Register",3);
            
                while(nickName.equals("")){
                    nickName = JOptionPane.showInputDialog(MenuDisplay.this, "Nothing was entered, write your nickname:", "Registro",3);
                }
            }
        });
        
        JButton exitButton = new JButton("Salir");
        exitButton.setPreferredSize(new Dimension(1000, 100));
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 25));
        exitButton.setOpaque(true);
        exitButton.setForeground(new Color(255, 255, 255));
        exitButton.setBackground(new Color(128, 128, 128));
        exitButton.setFocusPainted(false);
        this.getContentPane().add(exitButton, BorderLayout.SOUTH);
        exitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            System.exit(0);
            }
        });
        
        pack();
    }
    
    /**
     * Displays a pop up message telling the server is full.
     */
    public void standBy(){
        JOptionPane.showOptionDialog(null, "The server is currently full. When the game session finishes this window will close automatically.","Full Server", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null); 
    }
    
    /**
     * Returns the nickname entered by the player.
     * @return {@link MenuDisplay#nickName}
     */
    public String getNickName() {
        return nickName;
    }
    
    
}
