package visualFrames;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
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
     *Contains the grid's painted lines for player 1.
     */
    private static LinkedList<Line2D.Double> lineList1;
    
    /**
     *Contains the grid's painted lines for player 2.
     */
    private static LinkedList<Line2D.Double> lineList2;
    
    /**
     *Contains the grid's painted linked figures for player 1.
     */
    private static LinkedList<Path2D.Double> figureList1;
    
    /**
     *Contains the grid's painted linked figures for player 2.
     */
    private static LinkedList<Path2D.Double> figureList2;
    
    /**
     *Contains all the grid's figures areas.
     */
    private static LinkedList<Area> figuresArea;
    
    /**
     *Contains all the figures's dots.
     */
    private static LinkedList<Point> figuresDots;
    
    /**
     *Collection that contains a dot number as key and his location as value.
     */
    private final HashMap<Integer, Point> dotsLocations;
    
    /**
     *The first dot position that is clicked before making a line.
     */
    private int firstLinkDot;
    
    /**
     *The second dot position that is clicked before making a line.
     */
    private int secondLinkDot;
    
    /**
     *Indicates if a new line has been linked.
     */
    private boolean linked = false;
    
    /**
     *Color asigned to the player running this GameFrame.
     */
    private Color playerColor;
    
    /**
     *Color asigned to the player running the other GameFrame.
     */
    private Color otherPlayerColor;
    
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
     * @param playerNumber Number of the player.
     */
    public GameFrame(int playerNumber){
        setPreferredSize(new Dimension(800, 600));  
        setBackground(new Color(248, 248, 248));
        setLayout(new GridLayout(5,5));
        setVisible(true);
        
        this.lineList1 = new LinkedList<Line2D.Double>();
        this.lineList2 = new LinkedList<Line2D.Double>();
        this.figureList1 = new LinkedList<Path2D.Double>();
        this.figureList2 = new LinkedList<Path2D.Double>();
        
        this.DotsList = new LinkedList<JButton>();

        this.figuresArea = new LinkedList<Area>();
        this.figuresDots = new LinkedList<Point>();
        
        dotsLocations = new HashMap<Integer, Point>();
        fillLocationsHashMap();
        
        if(playerNumber == 1){
            this.playerColor = dotsBlue;
            this.otherPlayerColor = dotsOrange;
            
        }else{
            this.playerColor = dotsOrange;
            this.otherPlayerColor = dotsBlue;
        }    
        createGrid();
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
    public void linkDots(int startPoint, int endPoint, int playerNumber) {
        Line2D.Double newLine = new Line2D.Double(dotsLocations.get(startPoint), dotsLocations.get(endPoint));
        
        if(overlaps(newLine)){
            JOptionPane.showMessageDialog(GameFrame.this, "Invalid, line overlaps other."); 
            this.firstLinkDot = 0;
            this.secondLinkDot = 0;
        }else{
            if(playerNumber == 1){
                lineList1.add(newLine);
            }else{
                lineList2.add(newLine);
                
            }
            this.linked = true;
        }
        repaint();
        
        
    }
    
    /**
     * Detects if a given line overlaps the existing painted lines.
     * @param newline A new line that the player has drawn.
     */
    private boolean overlaps(Line2D.Double newline){
        boolean intersect = false;
        
        for(LinkedListNode node = lineList1.getFirstNode(); node != null;
            node = node.getNextNode()){
            Line2D.Double paintedLine = (Line2D.Double) node.getData();
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
        dot.setFocusPainted(false);
        
        dot.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                dot.setIcon(new ImageIcon("dot2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                dot.setIcon(new ImageIcon("dot1.png"));
            }

            @Override
            public void mousePressed(MouseEvent e){
          
                
                int dotNumber = getDotPosition(dot);
                
                Point dotLocation = dotsLocations.get(dotNumber);
                
                Point dotPlus1 = dotsLocations.get(dotNumber+1);
                Point dotMinus1 = dotsLocations.get(dotNumber-1);
                Point dotPlus5 = dotsLocations.get(dotNumber+5);
                Point dotMinus5 = dotsLocations.get(dotNumber-5);
                
                if(onTurn){
                    
                    if(insideFigure(dotLocation) &&  (!(inBorderFigures(dotLocation)))  || inFiguresDots(dotLocation) > 2){
                        
                        JOptionPane.showMessageDialog(GameFrame.this, "Invalid, dot locked inside figure."); 
                        firstLinkDot = 0;
                        secondLinkDot = 0;
                        
                    }else{
                        
                        if(firstLinkDot == 0){
                        firstLinkDot = dotNumber;
                        }else{
                            if(isValid(firstLinkDot,dotNumber)){
                                secondLinkDot = dotNumber;
                                linkDots(firstLinkDot,secondLinkDot,1);
                            }else{
                            firstLinkDot = 0;
                            JOptionPane.showMessageDialog(GameFrame.this, "Invalid link, out of range.");   
                            }
                        }
                    }     
                }
            }
        });
        this.add(dot);
        this.DotsList.add(dot);
    }
    
    
    /**
     *Checks if a point is inside any of thefigures in the grid.
     * @param point A class Point variable. 
     */
    public static boolean insideFigure(Point point){

        for(LinkedListNode node = figuresArea.getFirstNode(); node != null;
                node = node.getNextNode()){
                    Area area = (Area) node.getData();
                    if(area.contains(point) && !(inBorderFigures(point))){
                        return true;
                    } 
                }
        return false;
    }
    
    /**
     *Detects if a point is part of the edge the figures in the grid.
     * @param newPoint A class Point variable.
     */
    public static boolean inBorderFigures(Point newPoint){
        
        for(LinkedListNode node = figuresDots.getFirstNode(); node != null;
                node = node.getNextNode()){
                    Point registeredPoint = (Point) node.getData();
                    if(newPoint.equals(registeredPoint)){
                        return true;
                    } 
                }
        return false;
    }
    /**
     * Counts the times a point is repeated in figuresDots list.
     * @param point A class Point variable.
     */
    public static int inFiguresDots(Point point){
        int n = 0;
        for(LinkedListNode node = figuresDots.getFirstNode(); node != null;
                node = node.getNextNode()){
                    Point listPoint = (Point) node.getData();
                    if(point.equals(listPoint)){
                        n++;
                    } 
                }
        return n;
    }
    
    
    
    /**
     * Detects and removes a point in the linked list figureDots if it 
     * is currently contained in aa figure.
     */
    public static void updateBorderFigures(){
        
        for(LinkedListNode node = figuresDots.getFirstNode(); node != null;
                node = node.getNextNode()){
                    Point point = (Point) node.getData();
                    if(insideFigure(point)){
                        node.setData(new Point(0,0));
                    } 
        }
    }
    
    /**
     * Generate a figure by giving his dot members.
     * @param list A list containing all the dot's locations who will constitute the Figure
     */
    public void generateFigure(LinkedList<Integer> list, int playerNumber) {
        
        Path2D.Double newPath = new Path2D.Double();
        
        boolean first = true;
        for(LinkedListNode node = list.getFirstNode(); node != null;
                
            node = node.getNextNode()){
            
            Integer n = (Integer) node.getData();
            
            figuresDots.add(dotsLocations.get(n));
            
            if(first){
                newPath.moveTo(dotsLocations.get(n).x,dotsLocations.get(n).y);
                first = false;
            }else{
                newPath.lineTo(dotsLocations.get(n).x,dotsLocations.get(n).y);
            }
        }
        newPath.closePath();
        
        Area newArea = new Area(newPath);
        figuresArea.add(newArea);
        
        updateBorderFigures();
        
        if(playerNumber == 1){
            figureList1.add(newPath);
        }else{
            figureList2.add(newPath);
        }
        
        repaint();
    }  
    
    /**
     * Overrided method to paintComponents.
     */
    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g.create();
            
            for(LinkedListNode node = figureList1.getFirstNode(); node != null;
            node = node.getNextNode()){
                Path2D.Double path = (Path2D.Double) node.getData();
                if(getPlayerNumber()==1){
                    g2D.setPaint(dotsOrange);
                }else{
                    g2D.setPaint(dotsBlue);
                }
                g2D.fill(path);
                g2D.draw(path);
            }
            
            for(LinkedListNode node = figureList2.getFirstNode(); node != null;
            node = node.getNextNode()){
                Path2D.Double path = (Path2D.Double) node.getData();
                if(getPlayerNumber()==1){
                    g2D.setPaint(dotsBlue);
                }else{
                    g2D.setPaint(dotsOrange);
                }
                        
                g2D.fill(path);
                g2D.draw(path);
            }
            
            for(LinkedListNode node = lineList1.getFirstNode(); node != null;
            node = node.getNextNode()){
                Line2D.Double line = (Line2D.Double) node.getData();
                g2D.setColor(playerColor);
                g2D.setStroke(new BasicStroke(6));
                g2D.draw(line);
            } 
            
            for(LinkedListNode node = lineList2.getFirstNode(); node != null;
            node = node.getNextNode()){
                Line2D.Double line = (Line2D.Double) node.getData();
                g2D.setColor(otherPlayerColor);
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
     * Returns the status of the linked variable.
     * @return linked {@link GameFrame#linked}
     */
    public boolean getLinked() {
        return linked;
    }
    
    /**
     * Returns the variable firstLinkDot.
     * @return firstLinkDot {@link GameFrame#firstLinkDot}
     */
    public int getFirstLinkDot() {
        return firstLinkDot;
    }
    
    /**
     * Returns the variable secondLinkDot.
     * @return secondLinkDot {@link GameFrame#secondLinkDot}
     */
    public int getSecondLinkDot() {
        return secondLinkDot;
    }
    
    /**
     * Resets all the values needed to paint a new line.
     */
    public void resetLinks(){
        this.linked = false;
        this.firstLinkDot = 0;
        this.secondLinkDot = 0;
    }
    
    /**
     * Returns a linked list with the painted lines.
     * @return {@link GameFrame#lineList}
     */
    public static LinkedList<Line2D.Double> getLineList() {
        return lineList2;
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
    public static LinkedList<Path2D.Double> getFiguresList() {
        return figureList2;
    }       
}
