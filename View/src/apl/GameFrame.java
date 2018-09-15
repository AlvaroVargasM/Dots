package apl;


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
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *Interactible display of the dots grid.
 */
public class GameFrame extends JPanel{
    
    /**
     *Indicates if the user is player 1 or player 2.
     */
    private int playerNumber;
    
    /**
     *Indicates if the user is on turn.
     */
    private boolean onTurn;
    
    /**
     *Contains all the grid's dots.
     */
    private static LinkedList<JButton> DotsList;
    
    /**
     *Contains all the grid's painted lines.
     */
    private static LinkedList<Line2D.Double> lineList;
    
    /**
     *Contains all the grid's painted linked figures.
     */
    private static LinkedList<GeneralPath> linkedFiguresList;
    
    /**
     *Collection that contains a dot number as key and his location as value.
     */
    private final HashMap<Integer, Point> dotsLocations;
    
    /**
     *The first JButton that is clicked before making a line.
     */
    private JButton firstLinkDot;
    
    /**
     *Color asigned to the player.
     */
    private Color playerColor;
    
    /**
     *Final variable that holds a blue color.
     */
    private static final Color dotsBlue = new Color(21, 72, 144);
    
    /**
     *Final variable that holds a orange color.
     */
    private static final Color dotsOrange = new Color(255, 102, 0);   
    
    /**
     *The constructor of the class GameFrame, recieves no parameters.
     */
    public GameFrame(){

        setPreferredSize(new Dimension(800, 600));  
        setBackground(new Color(248, 248, 248));
        setLayout(new GridLayout(5,5));
        setVisible(true);
        
        this.lineList = new LinkedList<Line2D.Double>();
        this.DotsList = new LinkedList<JButton>();
        this.linkedFiguresList = new LinkedList<GeneralPath>();
        
        dotsLocations = new HashMap<Integer, Point>();
        fillLocationsHashMap();
              
        this.playerColor = this.dotsOrange;
          
        createGrid();
        
        generateFigure(new LinkedList<Integer>(Arrays.asList(1,2,6)));
    }
    
    /**
     *Creates the grid by adding dots.
     */
    private void createGrid(){
        for(int pos = 1; pos < 26; pos++)addDot(pos);
    }
    
    /**
     *Fills a Hash Map with the number of identification of each button and the coordinates of his center.
     */
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
      
    /**
     *Function that returns a boolean indicating if the connection between two dots meets the game's rules.
     * @param dot1 Dot from which the line begins.
     * @param dot1 Ending dot of the line.
     * @return A boolean indicating the validity the link between the dots.
     */
    private boolean isValid(int dot1, int dot2){
        if( dot2 == dot1+1 || dot2 == dot1-1 || dot2 == dot1+4 || dot2 == dot1-4 || dot2 == dot1+5 || dot2 == dot1-5 || dot2 == dot1+6 || dot2 == dot1-6){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Returns the position of a given JButton in the panel.
     * @param button Button whose location is wanted.
     * @return The dot's location number.
     */
    private int getDotPosition(JButton button){
        return Integer.parseInt(button.getName());
    }
    
    /**
     * Create the linked line and adds its to a list of lines.
     * @param startPoint Dot's starting point.
     * @param endPoint Dot's finishing point.
     */
    private void linkDots(Point startPoint, Point endPoint) {
        Line2D.Double newLine = new Line2D.Double(startPoint, endPoint);
        
        if(overlaps(newLine)){
            JOptionPane.showMessageDialog(GameFrame.this, "Invalid, line overlaps other."); 
        }else{
            lineList.add(newLine);
        }
        repaint();
        firstLinkDot = null;
    }
    
    /**
     * Detecs if a given line overlaps the existing painted lines.
     * @param newline A new line that the player has drawn.
     */
    private boolean overlaps(Line2D.Double newline){
        boolean intersect = false;
        for(Line2D.Double paintedLine: this.lineList){
            
           if(newline.intersectsLine(paintedLine)){
               Point2D start1 = paintedLine.getP1();
               Point2D end1 = paintedLine.getP2(); 
               Point2D start2 = newline.getP1();
               Point2D end2 = newline.getP2(); 
               
               if(start1.equals(start2) || start1.equals(end2) || end1.equals(start2) || end1.equals(end2)){
                  intersect = false; 
               }else{
                   intersect = true;
                   break;
               }
           }
        }
        if(intersect){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     *Add the dot to GameFrame.
     * @param position The position of the dot within the 5x5 grid.
     */
    private void addDot(int position){
        JButton dot = new JButton(new ImageIcon("dot1.png"));

        dot.setName(String.valueOf(position));
        dot.setBorderPainted(false);
        dot.setContentAreaFilled(false);
        //dot.setFocusPainted(false);
        
        dot.addMouseListener(new MouseAdapter() {
            /**
             * Overrived method of MouseListener, it activates when the mouse enters the dot's area.
             * @param e Generic mouse event of Mouse Listener.
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                dot.setIcon(new ImageIcon("dot2.png"));
            }
            /**
             * Overrived method of MouseListener, it activates when the mouse exits the dot's area.
             * @param e Generic mouse event of Mouse Listener.
             */
            @Override
            public void mouseExited(MouseEvent e) {
                dot.setIcon(new ImageIcon("dot1.png"));
            }
  
            /**
             * Overrived method of MouseListener, it activates when the dot is clicked. 
             * @param e Generic mouse event of Mouse Listener.
             */
            @Override
            public void mousePressed(MouseEvent e){
                if(firstLinkDot == null){
                    firstLinkDot = dot;
                }else{
                    if(isValid(getDotPosition(firstLinkDot),getDotPosition(dot))){
                        linkDots(dotsLocations.get(getDotPosition(firstLinkDot)),dotsLocations.get(getDotPosition(dot))); 
                    }else{
                     firstLinkDot = null;
                     JOptionPane.showMessageDialog(GameFrame.this, "Invalid link, out of range.");   
                    }
                }
            }
        });
        this.add(dot);
        this.DotsList.add(dot);
    }
    
    /**
     * Generate a figure by giving his dot members.
     * @param list A list containing all the dot's locations who will constitute the Figure
     */
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
    
    /**
     * Overrided method to paintComponents.
     * @param g Generic instance of Graphics to paint the components
     */
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
        
    /**
     * Returns the player's number.
     * @return playerNumber {@link GameFrame#playerNumber}
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
    /**
     * Sets the player's number.
     * @param playerNumber {@link GameFrame#playerNumber}
     */
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    /**
     * Returns the player turn's boolean state.
     * @return onTurn {@link GameFrame#onTurn}
     */
    public boolean getOnTurn() {
        return onTurn;
    }
    /**
     * Sets the player turn's boolean state.
     * @param onTurn {@link GameFrame#onTurn}
     */
    public void setOnTurn(boolean onTurn) {
        this.onTurn = onTurn;
    }
    /**
     * Returns a linked list with the painted lines.
     * @return {@link GameFrame#lineList}
     */
    public static LinkedList<Line2D.Double> getLineList() {
        return lineList;
    }
    /**
     * Returns a linked list with the painted lines.
     * @return {@link GameFrame#DotsList}
     */
    public static LinkedList<JButton> getDotsList() {
        return DotsList;
    }
    /**
     * Returns a linked list with the painted figures.
     * @return {@link GameFrame#linkedFiguresList}
     */
    public static LinkedList<GeneralPath> getLinkedFiguresList() {
        return linkedFiguresList;
    }       
}
