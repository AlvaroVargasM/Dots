package Path;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.GridLayout;

import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GameFrame extends JPanel{
    
    private JLabel Header;
    private JLabel p1Name;
    private JLabel p2Name;
    private JLabel p1Score;
    private JLabel p2Score;
    private JLabel TurnLabel;
    private JLabel TurnNumber;
    
    private final LinkedList<Line2D.Double> lineList;
    private final LinkedList<JButton> buttonList;
    private final LinkedList<GeneralPath> linkedFiguresList;
    
    private final HashMap<Integer, Point> dotsLocations;
    
    private JButton firstLinkButton;

    private Color playerColor;
    
    private static final Color dotsBlue = new Color(21, 72, 144);
    private static final Color dotsOrange = new Color(255, 102, 0);   
    
    
    public GameFrame(){

        
        setPreferredSize(new Dimension(800, 600));  
        setBackground(new Color(248, 248, 248));
        setLayout(new GridLayout(5,5));
        setVisible(true);
        
        this.lineList = new LinkedList<Line2D.Double>();
        this.buttonList = new LinkedList<JButton>();
        this.linkedFiguresList = new LinkedList<GeneralPath>();
        
        dotsLocations = new HashMap<Integer, Point>();
        fillLocationsHashMap();
              
        this.playerColor = this.dotsOrange;
          
        createGrid();
        
        generateFigure(new LinkedList<Integer>(Arrays.asList(2,4,10)));
    }
    
    private boolean isValid(int dot1, int dot2){
        if( dot2 == dot1+1 || dot2 == dot1-1 || dot2 == dot1+4 || dot2 == dot1-4 || dot2 == dot1+5 || dot2 == dot1-5 || dot2 == dot1+6 || dot2 == dot1-6){
            return true;
        }else{
            return false;
        }
    }

    private void createGrid(){
        for(int pos = 1; pos < 26; pos++)addButton(pos);
    }
    
    private void fillLocationsHashMap(){
        int x = 80;
        int y = 60;
        for(int n=1; n < 26; n++){           
            dotsLocations.put(n, new Point(x,y));
            if(n % 5 == 0){
                x = 80;
                y += 120; 
            }else{
                x +=160;
            }
        }
    }
    
    private void addButton(int pos){
        JButton button = new JButton(new ImageIcon("dot1.png"));

        button.setName(String.valueOf(pos));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setIcon(new ImageIcon("dot2.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(new ImageIcon("dot1.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e){
                if(firstLinkButton == null){
                    firstLinkButton = button;
                }else{
                    if(isValid(getButtonId(firstLinkButton),getButtonId(button))){
                        linkDots(dotsLocations.get(getButtonId(firstLinkButton)),dotsLocations.get(getButtonId(button))); 
                    }else{
                     firstLinkButton = null;
                     JOptionPane.showMessageDialog(GameFrame.this, "Invalid link.");   
                    }
                }
            }
        });
        this.add(button);
        this.buttonList.add(button);
    }
    
    private int getButtonId(JButton button){
        return Integer.parseInt(button.getName());
    }
    
    protected void generateFigure(LinkedList<Integer> list) {
        
        GeneralPath newPath = new GeneralPath();
        
        boolean first = true;
        for(Integer n: list){           
            if(first){
                newPath.moveTo(dotsLocations.get(n).x,dotsLocations.get(n).y);
                first = false;
            }else{
                newPath.lineTo(dotsLocations.get(n).x,dotsLocations.get(n).y);
            }
        }
        newPath.closePath();
        linkedFiguresList.add(newPath);
        repaint();
    }
    
    protected void linkDots(Point startPoint, Point endPoint) {
        
        Line2D.Double line = new Line2D.Double(startPoint, endPoint);
        lineList.add(line);
        repaint();
        firstLinkButton = null;
    }
    
    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g.create();
            
            for(GeneralPath path: linkedFiguresList){ 
                g2D.setPaint(playerColor);
                g2D.fill(path);
                g2D.draw(path);
            }
            
            for(Line2D.Double line: lineList){
                g2D.setColor(playerColor);
                g2D.setStroke(new BasicStroke(6));
                g2D.draw(line);
            } 
        } 
}
