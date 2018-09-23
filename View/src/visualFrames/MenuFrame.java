package visualFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

/**
 * Main meu of the application.
 */
public class MenuFrame extends JFrame{
    
    /**
     * Nick name of the player.
     */
    String nickName;
    
    /**
     * Server's ip adress.
     */
    String serverIp;
    
    /**
     * Menu's start game button.
     */
    JButton startButton;
    
    /**
     * Constructor of the class MenuFrame, recieves no parameters.
     */
    public MenuFrame (){
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
        
        startButton = new JButton("Start game");
        startButton.setPreferredSize(new Dimension(1000, 300));
        startButton.setFont(new Font("Tahoma", Font.BOLD, 35));
        startButton.setOpaque(true);
        startButton.setForeground(new Color(21, 72, 144));
        startButton.setBackground(Color.WHITE);
        startButton.setFocusPainted(false);
        this.getContentPane().add(startButton, BorderLayout.CENTER);
        
        startButton.setFocusable(true);
        startButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(nickName.equals("")){
                    register();
                    }
                }
            } 
        });
        
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(nickName.equals("")){
                    register();
                }
            }
        });
        
        JButton exitButton = new JButton("Exit");
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
        JOptionPane.showOptionDialog(null, "The server is currently full. Please close this window to refresh.","Full Server", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null); 
    }
    
    /**
     * Displays two pop ups to set the server ip adress and the user's nickname.
     */
    private void register(){
        
        try {
            serverIp = JOptionPane.showInputDialog(MenuFrame.this, "Please enter the server's ip adress to connect:", "Register",3);
            
            if(!(serverIp.equals(JOptionPane.CANCEL_OPTION))){
                while(serverIp.equals("")){
                    serverIp = JOptionPane.showInputDialog(MenuFrame.this, "No ip was entered, try again:", "Registro",3);
                }
                
                try{
                nickName = JOptionPane.showInputDialog(MenuFrame.this, "Enter your nick name: ", "Register",3);
                
                if(!(nickName.equals(JOptionPane.CANCEL_OPTION)))   
            
                    while(nickName.equals("")){
                        nickName = JOptionPane.showInputDialog(MenuFrame.this, "Nothing was entered, write your nickname:", "Registro",3);
                    }
                }catch(NullPointerException e){
                    nickName = "";
                }       
            }
        }catch(NullPointerException e){
            serverIp = "";
        }    
    }
    
    /**
     * Changes startButton's display text.
     * @param text New startButton's text
     */
    public void setText(String text){
        startButton.setText(text);
    }
    /**
     * Returns the ip adress entered by the player.
     * @return {@link MenuFrame#serverIp}
     */
    public String getServerIp() {
        return serverIp;
    }
    
    /**
     * Returns the nickname entered by the player.
     * @return {@link MenuFrame#nickName}
     */
    public String getNickName() {
        return nickName;
    }
}
