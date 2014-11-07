package com.chuidiang.ejemplos.app_main.internal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

public class MainWindow {
   private JFrame window;
   private JTabbedPane tabbedPane;
   public MainWindow(final ExampleServiceImpl exampleServiceImpl) {
         SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
               window = new JFrame("Main application");
               tabbedPane = new JTabbedPane();
               tabbedPane.add("main",new MainPanel(exampleServiceImpl));
               window.getContentPane().add(tabbedPane);
               window.pack();
               window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
               window.addWindowListener(new WindowAdapter() {
                  @Override
                  public void windowClosing(WindowEvent arg0) {
                     exampleServiceImpl.exit();
                  }
               });
               
               
               window.setLocationRelativeTo(null);
               window.setVisible(true);
            }
         });
   }
   
   public void addPlugin(final BundleAndMap plugin) {
      System.out.println("Adding pane");
      SwingUtilities.invokeLater(new Runnable() {
         
         @Override
         public void run() {
            tabbedPane.add(plugin.pluginProperties.get("component.name").toString(),
                  plugin.plugin.getComponent());
            
         }
      });
      
   }
   public void removePlugin(PluginInterface plugin) {
      System.out.println("Removing pane");
      tabbedPane.remove(plugin.getComponent());
   }
   
   public void exit(){
      window.setVisible(false);
      window.dispose();
   }
}  
