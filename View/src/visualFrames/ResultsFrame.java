package visualFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Display that show the final game session's statistics.
 */
public class ResultsFrame extends JFrame{
    
    /**
     *Final variable that holds customized font type number one.
     */
    private static final Font font1 = new Font("Tahoma", Font.ITALIC, 28);
    
    /**
     *Final variable that holds customized font type number two.
     */
    private static final Font font2 = new Font("Tahoma", Font.BOLD, 33);
    
    /**
     *Final variable that holds font type number two.
     */
    private static final Color blue = new Color(21, 72, 144);
    
    /**
     *Final variable that holds a orange color.
     */
    private static final Color orange = new Color(255, 102, 0);
    
    
    /**
     * @param winPlayer Nickname of the game session winner.
     * @param player1 Player one nickname.
     * @param player2 Player two nickname.
     * @param score1 Player one score.
     * @param score2 Player two score.
     * @param turns Final total of turns.
     */
    public ResultsFrame(String winPlayer,String player1, String player2, String score1, String score2, String turns){
        
        setTitle("Results"); 
        setSize(1000, 600); 
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("Results",SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 75));
        title.setPreferredSize(new Dimension(1000, 100));
        title.setOpaque(true);
        title.setForeground(new Color(255, 255, 255));
        title.setBackground(new Color(255, 102, 0));
        add(title, BorderLayout.NORTH);
        
        JPanel stats = new JPanel(){
            @Override
                public void paintComponent(Graphics g){
                super.paintComponent(g);
                Image background = Toolkit.getDefaultToolkit().getImage("dotsBackground.jpg");
                g.drawImage(background, 0, 0, 1000, 600, this);
                }
            };
        stats.setPreferredSize(new Dimension(1000, 500));
        stats.setLayout(new GridLayout(4,2));
        
        
        JLabel winnerLabel = new JLabel("Winner:      ",SwingConstants.RIGHT);
        winnerLabel.setFont(font1);
        winnerLabel.setForeground(blue);
        JLabel winner = new JLabel(winPlayer);
        winner.setFont(font2);
        winner.setForeground(orange);
        JLabel p1Name = new JLabel(player1+":      ",SwingConstants.RIGHT);
        p1Name.setFont(font1);
        p1Name.setForeground(blue);
        JLabel p2Name = new JLabel(player2+":      ",SwingConstants.RIGHT);
        p2Name.setFont(font1);
        p2Name.setForeground(blue);
        JLabel p1Score = new JLabel(score1+" pts");
        p1Score.setFont(font2);
        p1Score.setForeground(orange);
        JLabel p2Score = new JLabel(score2+" pts");
        p2Score.setFont(font2);
        p2Score.setForeground(orange);
        JLabel TurnLabel = new JLabel("Total of turns:      ",SwingConstants.RIGHT);
        TurnLabel.setFont(font1);
        TurnLabel.setForeground(blue);
        JLabel TurnNumber = new JLabel(turns);
        TurnNumber.setFont(font2);
        TurnNumber.setForeground(orange);
        
        stats.add(winnerLabel);
        stats.add(winner);
        stats.add(p1Name);
        stats.add(p1Score);
        stats.add(p2Name);
        stats.add(p2Score);
        stats.add(TurnLabel);
        stats.add(TurnNumber);
        
        add(stats, BorderLayout.CENTER);
        
        pack();
    }
} 

