package com.chuidiang.ejemplos.plugin2.internal;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chuidiang.ejemplos.plugin_interface.PluginInterface;

/**
 * Internal implementation of our example OSGi service
 */
public final class ExampleServiceImpl
    implements PluginInterface
{
  
   private JPanel panel;
   private JTextField operand1;
   private JTextField operand2;
   private JButton addButton;
   private JTextField result;

   public void stop(){
      
   }
   
   public void start() {
      panel = new JPanel(new FlowLayout());
      operand1 = new JTextField(5);
      operand2 = new JTextField(5);
      addButton = new JButton("+");
      result = new JTextField(5);
      panel.add(operand1);
      panel.add(operand2);
      panel.add(addButton);
      panel.add(result);
      
      addButton.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent arg0) {
            try {
               int value1 = Integer.parseInt(operand1.getText());
               int value2 = Integer.parseInt(operand2.getText());
               result.setText(Integer.toString(value1+value2));
            } catch (Exception e){
               System.err.println("Some input is wrong");
            }
            
         }
      });
   }
   
   @Override
   public String getName() {
      return "plugin2";
   }

   @Override
   public Component getComponent() {
      return panel;
   }
}

