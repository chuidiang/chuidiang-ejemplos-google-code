package com.chuidiang.ejemplos.app_main.internal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
   private static final long serialVersionUID = 1270895828974126514L;
   JButton button = new JButton("new plugin");
   JFileChooser fileChooser = new JFileChooser();
   private PluginInstallation pluginInstaller;

   public MainPanel(PluginInstallation pluginInstaller) {
      this.pluginInstaller = pluginInstaller;
      new BoxLayout(this, BoxLayout.Y_AXIS);
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
         pluginInstaller.installPlugin(path);
         JLabel label = new JLabel(file.getName());
         add(label);
      }
   }
}