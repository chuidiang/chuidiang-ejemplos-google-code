package com.chuidiang.ejemplos.app_main.internal;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
   private static final long serialVersionUID = 1270895828974126514L;
   JButton button = new JButton("new plugin");
   JFileChooser fileChooser = new JFileChooser("d:/javier/proyectos/ejemplo-osgi/osgi-app");
   private PluginInstallation pluginInstaller;
   private Map<Long, JPanel> panels = new HashMap<Long, JPanel>();

   public MainPanel(PluginInstallation pluginInstaller) {
      this.pluginInstaller = pluginInstaller;
      new BoxLayout(this, BoxLayout.X_AXIS);
      add(button);
      button.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            chooseFile();

         }
      });
   }

   protected void chooseFile() {
      int option = fileChooser.showOpenDialog(this);
      if (option == JFileChooser.APPROVE_OPTION) {
         File file = fileChooser.getSelectedFile();
         String path = "file:///" + file.getAbsolutePath();
         path = path.replaceAll("\\\\", "/");
         System.out.println(path);
         final long bundleId = pluginInstaller.installPlugin(path);
         if (panels.containsKey(bundleId)){
            System.err.println("Bundle already added");
            return;
         }
         System.out.println("Installed "+bundleId);
         JPanel panel = new JPanel(new FlowLayout());
         JLabel label = new JLabel(file.getName());
         JButton button = new JButton("uninstall");
         button.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
               System.out.println("removing "+bundleId);
               pluginInstaller.uninstall(bundleId);
               JPanel panel = panels.remove(bundleId);
               remove(panel);
               
            }
         });
         panel.add(label);
         panel.add(button);
         add(panel);
         panels.put(bundleId, panel);
      }
   }

}