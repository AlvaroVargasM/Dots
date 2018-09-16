package visualFrames;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Conatins the game session's stats.
 */
public class InfoFrame extends JPanel{
    
    /**
     * Player 1 name.
     */
    private JLabel p1Name;
    
    /**
     * Player 2 name.
     */
    private JLabel p2Name;
    
    /**
     * Player 1 score.
     */
    private JLabel p1Score;
    
    /**
     * Player 2 score.
     */
    private JLabel p2Score;
    
    /**
     * Holds the turn's number.
     */
    private JLabel TurnNumber;
    
    /**
     * Constructor of the InfoFrame class, recieves no parameters.
     */
    public InfoFrame(){
        setPreferredSize(new Dimension(200, 600));  
        setBackground(new Color(255, 255, 255));
        setLayout(new GridLayout(7,1));
        locateLabels();
    }
    
    /**
     * Locate all the information labels in the frame.
     */
    private void locateLabels(){
        
        Font subHeaderFont = new Font("Tahoma", Font.PLAIN, 22);
        Font NumbersFont = new Font("Tahoma", Font.ITALIC, 18);
        
        JLabel header = new JLabel("Scores",SwingConstants.CENTER);
        header.setFont(new Font("Tahoma", Font.BOLD, 33));
        header.setForeground(new Color(21, 72, 144));
        
        p1Name = new JLabel("»Player 1:",SwingConstants.CENTER);
        p1Name.setFont(subHeaderFont);
        p1Name.setForeground(new Color(255, 102, 0));

        p2Name = new JLabel(" Player 2:",SwingConstants.CENTER);
        p2Name.setFont(subHeaderFont);
        p2Name.setForeground(new Color(51, 119, 255));
           
        JLabel turnLabel = new JLabel("Turn:",SwingConstants.CENTER);
        turnLabel.setFont(subHeaderFont);
        turnLabel.setForeground(new Color(21, 72, 144));
        
        p1Score = new JLabel("0",SwingConstants.CENTER);
        p1Score.setFont(NumbersFont);
        p1Score.setForeground(new Color(255, 102, 0));
        p1Score.setVerticalAlignment(JLabel.TOP);
        
        p2Score = new JLabel("0",SwingConstants.CENTER);
        p2Score.setFont(NumbersFont);
        p2Score.setForeground(new Color(51, 119, 255));
        p2Score.setVerticalAlignment(JLabel.TOP);
        
        TurnNumber = new JLabel("0",SwingConstants.CENTER);
        TurnNumber.setFont(NumbersFont);
        TurnNumber.setVerticalAlignment(JLabel.TOP);
        TurnNumber.setForeground(new Color(21, 72, 144));
        
        add(header);
        add(p1Name);
        add(p1Score);
        add(p2Name);
        add(p2Score);
        add(turnLabel);
        add(TurnNumber);
    }
    
    /**
     * Extracts and returns the text inside p1Name JLabel.
     * @return Text displaying by {@link InfoFrame#p1Name} in a String.
     */
    public String getP1Name() {
        return p1Name.getText();
    }
    
    /**
     * Sets the player's name in the JLabel.
     * @param name New player one name.
     */
    public void setP1Name(String name) {
        this.p1Name.setText(name);
    }
    
    /**
     * Extracts and returns the text inside p2Name JLabel.
     * @return Text displaying by {@link InfoFrame#p2Name} in a String.
     */
    public String getP2Name() {
        return p2Name.getText();
    }
    
    /**
     * Sets the player's name in the JLabel.
     * @param name New player two name.
     */
    public void setP2Name(String name) {
        this.p2Name.setText(name);
    }
 
    /**
     * Extracts and returns the number inside p1Score JLabel.
     * @return Text displaying by {@link InfoFrame#p1Score} in a int.
     */
    public int getP1Score() {
        return Integer.parseInt(this.p1Score.getText());
    }
    
    /**
     * Sets the player's score.
     * @param p1Score Player 1 score.
     */
    public void setP1Score(int p1Score) {
        this.p1Score.setText(Integer.toString(p1Score));
    }
    
    /**
     * Extracts and returns the number inside p2Score JLabel.
     * @return Text displaying by {@link InfoFrame#p2Score} in a int.
     */
    public int getP2Score() {
        return Integer.parseInt(p2Score.getText());
    }
    
    /**
     * Sets the player's score.
     * @param p2Score Player 2 score.
     */
    public void setP2Score(int p2Score) {
        this.p1Score.setText(Integer.toString(p2Score));
    }
    
    /**
     * Extracts and returns the number inside TurnNumber JLabel.
     * @return Text displaying by {@link InfoFrame#TurnNumber} in a int.
     */
    public int getTurnNumber() {
        return Integer.parseInt(this.TurnNumber.getText());
    }
    
    /**
     * Increase the game session's turn number by one.
     */
    public void increaseTurnNumber() {
        int updatedTurn = Integer.parseInt(this.TurnNumber.getText()) +1;
        this.p1Score.setText(Integer.toString(updatedTurn));
    }
    
}
