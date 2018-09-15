package apl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.GridLayout;
import java.awt.Insets;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.*;
/**
 *
 * @author luism
 */
public class MainFrame {
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
        MainFrame marco1 = new MainFrame();
    }
    
    private JFrame dotsFrame;
    
    /**
     *
     */
    public MainFrame(){
        //panel grande de fondo
        dotsFrame = new JFrame();
        dotsFrame.setTitle("Dots"); //ponemos titulo a la ventana
        dotsFrame.setSize(1000, 500); //asignamos alto y ancho de la ventana
        dotsFrame.setLocationRelativeTo(null);//la centramos con esta línea
        dotsFrame.setResizable(true);//impedimos que se cambie su tamaño
        dotsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//establecemos que se cierre la ventana al darle click a la equis roja 
        dotsFrame.setVisible(true);//hacemos la ventana visible
        
	InfoFrame infoFrame = new InfoFrame();
	dotsFrame.getContentPane().add(infoFrame, BorderLayout.WEST);
        
        GameFrame gameFrame = new GameFrame();
	dotsFrame.getContentPane().add(gameFrame, BorderLayout.EAST);
        
        dotsFrame.pack();
    }
        
}
