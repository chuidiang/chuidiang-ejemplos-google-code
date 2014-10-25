package com.chuidiang.ejemplos.plugin1.internal;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl implements PluginInterface {

   private JPanel panel;
   private JButton button;
   private JLabel label;
   private int counter=0;
   public void start() {

      System.out.println("plugin1 starting");
      panel = new JPanel(new FlowLayout());
      button = new JButton("Click me");
      panel.add(button);
      label = new JLabel(Integer.toString(counter));
      panel.add(label);
      button.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent arg0) {
            counter++;
            label.setText(Integer.toString(counter));
            
         }
      });
   }

   public void stop() {
      System.out.println("plugin1 stoping");
   }

   @Override
   public String getName() {
      // TODO Auto-generated method stub
      return "I'm plugin1, version 1";
   }

   @Override
   public Component getComponent() {
      return panel;
   }
}
