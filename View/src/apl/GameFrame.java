package apl;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.GridLayout;

import java.awt.Point;
import java.awt.Rectangle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Line2D;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    
    private JButton firstLinkButton;
    private JButton secondLinkButton;
    private Point startPoint;
    private Point endPoint;
    private Color lineColor;
    
    private static final Color dotsBlue = new Color(21, 72, 144);
    private static final Color dotsOrange = new Color(255, 102, 0);
    
    
    
    /*private Integer firstPosition;
    private Integer secondPosition;*/
            
    public GameFrame(){
        /*firstPosition = null;
        secondPosition = null;*/
        
        setPreferredSize(new Dimension(800, 600));  
        setBackground(new Color(248, 248, 248));
        setLayout(new GridLayout(5,5));
        setVisible(true);
        
        this.lineList = new LinkedList<Line2D.Double>();
        
        //esto cambiara segun el numero de jugador
        this.lineColor = this.dotsBlue;
          
        createGrid();
        
    }

    private void createGrid(){
        for(int pos = 0; pos < 25; pos++)addButton(pos);
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
                    secondLinkButton = button;
                    linkDots();   
                }
                /*if(firstPosition == null) firstPosition = pos;
                else{ 
                    secondPosition = pos;
                    System.out.println("(" + firstPosition + ", " + secondPosition + ")");
                    firstPosition = null;
                    secondPosition = null;
                }*/
            }
        });
        this.add(button);
    }
    
    //Usando la propiedad de nombre obtenemos un id unico para cada boton
    private int getButtonId(JButton button){
        return Integer.parseInt(button.getName());
    }
    
    protected Point getCenter(Rectangle bounds) {
        return new Point( bounds.x + (bounds.width / 2), bounds.y + (bounds.height / 2));

    }
    
    protected void linkDots() {

        this.startPoint = getCenter(firstLinkButton.getBounds());
        this.endPoint = getCenter(secondLinkButton.getBounds());
        
        Line2D.Double line = new Line2D.Double(startPoint, endPoint);
        lineList.add(line);
        
        //llama a correr el codigo dentro de paintComponent
        repaint();
        
        //Reiniciamos las variables de LinkButton
        firstLinkButton = null;
        secondLinkButton = null;

    }
    
    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2D = (Graphics2D) g.create();
            
            g2D.setColor(this.lineColor);
            g2D.setStroke(new BasicStroke(6));
            
            for(Line2D.Double line: lineList){
                g2D.draw(line);
            }
        }
        
}
