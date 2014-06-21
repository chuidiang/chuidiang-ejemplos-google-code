package org.chuidiang.chuwiki.worldwind;

import java.awt.BorderLayout;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwindx.examples.LayerPanel;

import javax.swing.JFrame;

public class MainMinimo extends JFrame {

   public MainMinimo() {
      WorldWindowGLCanvas wwd = new WorldWindowGLCanvas();
      wwd.setPreferredSize(new java.awt.Dimension(1000, 800));
      this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
      wwd.setModel(new BasicModel());
      
      LayerPanel panelCapas = new LayerPanel(wwd);
      this.getContentPane().add(panelCapas, BorderLayout.WEST);
   }

   public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            JFrame frame = new MainMinimo();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
         }
      });
   }
}