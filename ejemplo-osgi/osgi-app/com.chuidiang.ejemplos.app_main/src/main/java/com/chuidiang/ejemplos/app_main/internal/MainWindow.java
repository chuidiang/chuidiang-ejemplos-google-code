package com.chuidiang.ejemplos.app_main.internal;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

public class MainWindow {
   private JFrame window;
   private JTabbedPane tabbedPane;
   public MainWindow() {
         SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
               window = new JFrame("Main application");
               tabbedPane = new JTabbedPane();
               tabbedPane.add("main",new JLabel("La primera"));
               window.getContentPane().add(tabbedPane);
               window.pack();
               window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               window.setLocationRelativeTo(null);
               window.setVisible(true);
            }
         });
   }
   public void addPlugin(final PluginInterface plugin) {
      System.out.println("Adding pane");
      SwingUtilities.invokeLater(new Runnable() {
         
         @Override
         public void run() {
            tabbedPane.add(plugin.getName(),plugin.getComponent());
            
         }
      });
      
   }
   public void removePlugin(PluginInterface plugin) {
      System.out.println("Removing pane");
      tabbedPane.remove(plugin.getComponent());
      
   }
}  
