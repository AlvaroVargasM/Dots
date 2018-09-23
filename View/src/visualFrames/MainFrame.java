package visualFrames;
      
import java.awt.BorderLayout;
import javax.swing.*;

/**
 * Frame that contains both the game frame and information frame. 
 */
public class MainFrame extends JFrame{
    
    /*public static void main (String[] args){
        MainFrame marco = new MainFrame(1);
    }*/
    
    /**
     * Unique game frame containing the dots grid per player.
     */
    private GameFrame gameframe;
    
    /**
     * Unique information frame containing the game session's stats. 
     */
    private InfoFrame infoframe;
    
    /**
     *Constructor of the MainFrame class, recieves no parameters.
     */
    public MainFrame(int playerNumber){
        
        this.setTitle("Dots"); 
        this.setSize(1000, 600); 
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
	infoframe = new InfoFrame();
	this.getContentPane().add(infoframe, BorderLayout.WEST);
        
        gameframe = new GameFrame(playerNumber);
	this.getContentPane().add(gameframe, BorderLayout.EAST);
        
        this.pack();
    }
    
    /**
     * Returns the player's dots frame.
     * @return gameframe {@link MainFrame#gameframe}
     */ 
    public GameFrame getGameFrame() {
        return gameframe;
    }
    
    /**
     * Returns the player's information frame.
     * @return infoframe {@link MainFrame#infoframe}
     */ 
    public InfoFrame getInfoFrame() {
        return infoframe;
    }   
}
