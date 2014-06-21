package org.chuidiang.chuwiki.worldwind;

import java.awt.BorderLayout;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.layers.ViewControlsSelectListener;
import gov.nasa.worldwind.util.StatusBar;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
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

      StatusBar statusBar = new StatusBar();
      this.getContentPane().add(statusBar, BorderLayout.PAGE_END);
      statusBar.setEventSource(wwd);

      ViewControlsLayer viewControlsLayer = new ViewControlsLayer();
      ApplicationTemplate.insertBeforeCompass(wwd, viewControlsLayer);
      wwd.addSelectListener(new ViewControlsSelectListener(wwd,
            viewControlsLayer));

      pintaPoligonos(wwd, panelCapas);
   }

   private void pintaPoligonos(WorldWindowGLCanvas wwd, LayerPanel panelCapas) {
      CapaPoligonos capaPoligonos = new CapaPoligonos(wwd, panelCapas);
      capaPoligonos.pintaPoligono();
      capaPoligonos.pintaPoligonoExtruido();
      capaPoligonos.pintaPoligonoSuperficie();
   }

   public static void main(String[] args) {

      Configuration.setValue(AVKey.INITIAL_LATITUDE, Integer.valueOf(40));
      Configuration.setValue(AVKey.INITIAL_LONGITUDE, Integer.valueOf(-3));
      Configuration.setValue(AVKey.INITIAL_ALTITUDE, Integer.valueOf(190000));

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