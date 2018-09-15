package apl;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author luism
 */
public class InfoFrame extends JPanel{
    
    private JLabel header;
    private JLabel p1Name;
    private JLabel p2Name;
    private JLabel p1Score;
    private JLabel p2Score;
    private JLabel TurnLabel;
    private JLabel TurnNumber;
    
    /**
     *
     */
    public InfoFrame(){
        setPreferredSize(new Dimension(200, 600));  
        setBackground(new Color(255, 255, 255));
        setLayout(new GridLayout(7,1));
        
        initializeLabels();
    }

    private void initializeLabels(){
        
        Font subHeaderFont = new Font("Tahoma", Font.PLAIN, 22);
        Font NumbersFont = new Font("Tahoma", Font.ITALIC, 18);
        
        header = new JLabel("Scores",SwingConstants.CENTER);
        header.setFont(new Font("Tahoma", Font.BOLD, 33));
        header.setForeground(new Color(21, 72, 144));
        
        p1Name = new JLabel("»Player 1:",SwingConstants.CENTER);
        p1Name.setFont(subHeaderFont);
        p1Name.setForeground(new Color(255, 102, 0));

        p2Name = new JLabel(" Player 2:",SwingConstants.CENTER);
        p2Name.setFont(subHeaderFont);
        p2Name.setForeground(new Color(51, 119, 255));
           
        TurnLabel = new JLabel("Turn:",SwingConstants.CENTER);
        TurnLabel.setFont(subHeaderFont);
        TurnLabel.setForeground(new Color(21, 72, 144));
        
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
        add(TurnLabel);
        add(TurnNumber);
    }
    
}
