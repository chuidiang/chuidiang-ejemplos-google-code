package com.chuidiang.ejemplos.geotools;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.swing.JMapPane;
import org.geotools.swing.event.MapMouseEvent;
import org.geotools.swing.event.MapMouseListener;

public class MouseWheel {
   private static JMapPane pane;
   public static void initZoomWithMouseWheel(JMapPane pane){
      MouseWheel.pane = pane;
      pane.addMouseListener(new MapMouseListener() {
         
         @Override
         public void onMouseWheelMoved(MapMouseEvent arg0) {
            System.out.println(arg0.getWorldPos());
            
            int sign = arg0.getWheelAmount();
            System.out.println(sign);
            
            ReferencedEnvelope env = MouseWheel.pane.getDisplayArea();
            double width = env.getWidth();
            double delta = width * 0.1 * sign;
            
            env.expandBy(delta);
            MouseWheel.pane.setDisplayArea(env);
            MouseWheel.pane.repaint();
         }
         
         @Override
         public void onMouseReleased(MapMouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onMousePressed(MapMouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onMouseMoved(MapMouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onMouseExited(MapMouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onMouseEntered(MapMouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onMouseDragged(MapMouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
         
         @Override
         public void onMouseClicked(MapMouseEvent arg0) {
            // TODO Auto-generated method stub
            
         }
      });

   }
}
