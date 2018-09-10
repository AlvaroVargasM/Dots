package MainDisplay;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 *
 * @author luism
 */
public class GameFrame extends JPanel{
    
    private JLabel Header;
    private JLabel p1Name;
    private JLabel p2Name;
    private JLabel p1Score;
    private JLabel p2Score;
    private JLabel TurnLabel;
    private JLabel TurnNumber;
    
    public GameFrame(){
        setPreferredSize(new Dimension(800, 600));  
        setBackground(new Color(248, 248, 248));
        setLayout(new GridLayout(5,5));
        setVisible(true);
        
        initializeLabels();
    }

    private void initializeLabels(){
        
        addButton();addButton();addButton();addButton();addButton();addButton();addButton();addButton();addButton();addButton();
        addButton();addButton();addButton();addButton();addButton();addButton();addButton();addButton();addButton();addButton();
        addButton();addButton();addButton();addButton();addButton();
        /*JButton boton1 = new JButton("Picha");
        add(boton1);
        /*for(int i = 1; i > 3; i++){
            System.out.println("mae");
            /*String nombre = "boton"+i;
            add(new JButton(nombre));*/
    }
    
    private void addButton(){
        JButton button = new JButton(new ImageIcon("dot1.png"));
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setIcon(new ImageIcon("dot2.png"));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setIcon(new ImageIcon("dot1.png"));
            }
        });
        
        //button.setBorder(new RoundedBorder(500));
        //button.setOpaque(false);
        
        add(button);
    }
    
    /*private static class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}
    
    /*public class RoundButton extends JButton {
 
        public RoundButton(String label) {
            super(label);

            setBackground(Color.black);
            setFocusable(false);
            
            /*
            These statements enlarge the button so that it 
            becomes a circle rather than an oval.
            
            
            Dimension size = getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);

            /*
             This call causes the JButton not to paint the background.
             This allows us to paint a round background.
            
            setContentAreaFilled(false);
        }

        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
              g.setColor(Color.gray);
            } else {
              g.setColor(getBackground());
            }
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            g.setColor(Color.darkGray);
            g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        }

        // Hit detection.
        Shape shape;

        public boolean contains(int x, int y) {
            // If the button has changed size,  make a new shape object.
            if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new Ellipse2D.Float(0, 0, getWidth()/2, getHeight()/2);
            }
            return shape.contains(x, y);
        }
    }*/
}

