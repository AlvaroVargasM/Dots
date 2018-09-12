package apl;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author luism
 */
public class ResultsFrame extends JFrame{
    
    private JLabel winnerLabel;
    private JLabel winner;
    private JLabel p1Name;
    private JLabel p2Name;
    private JLabel p1Score;
    private JLabel p2Score;
    private JLabel TurnLabel;
    private JLabel TurnNumber;
    
//    public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		ResultsFrame marco1 = new ResultsFrame();
//    }
    
    
    public ResultsFrame(){
        setTitle("Results"); //ponemos titulo a la ventana
        setSize(1000, 500); //asignamos alto y ancho de la ventana
        setLocationRelativeTo(null);//la centramos con esta línea
        setResizable(true);//impedimos que se cambie su tamaño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//establecemos que se cierre la ventana al darle click a la equis roja 
        setVisible(true);//hacemos la ventana visible
        
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("Results",SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 33));
        title.setOpaque(true);
        title.setForeground(new Color(255, 255, 255));
        title.setBackground(new Color(255, 102, 0));
        add(title, BorderLayout.NORTH);
        
        JPanel stats = new JPanel();
        stats.setBackground(new Color(255,255,255));
        stats.setLayout(new GridLayout(4,2));
        
        JLabel winnerLabel = new JLabel("Winner:     ",SwingConstants.RIGHT);
        JLabel winner = new JLabel("Me");
        JLabel p1Name = new JLabel("Player 1:     ",SwingConstants.RIGHT);
        JLabel p2Name = new JLabel("Player 2:     ",SwingConstants.RIGHT);
        JLabel p1Score = new JLabel("28");
        JLabel p2Score = new JLabel("56");
        JLabel TurnLabel = new JLabel("Total of turns:     ",SwingConstants.RIGHT);
        JLabel TurnNumber = new JLabel("10");
        
        stats.add(winnerLabel);
        stats.add(winner);
        stats.add(p1Name);
        stats.add(p1Score);
        stats.add(p2Name);
        stats.add(p2Score);
        stats.add(TurnLabel);
        stats.add(TurnNumber);
        
        add(stats, BorderLayout.CENTER);
        
        //pack();
    }
    
}
