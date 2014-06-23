package org.chuidiang.chuwiki.worldwind;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.IconLayer;
import gov.nasa.worldwind.render.UserFacingIcon;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.LayerPanel;

import java.awt.Dimension;
import java.io.File;

public class CapaIconos {
   private WorldWindowGLCanvas wwd;
   private IconLayer capa;
   private final double LATITUD_INICIAL = 40.25;
   private final double LONGITUD_INICIAL = -3.25;
   private final double MAXIMO_DESPLAZAMIENTO = 0.1;

   private String iconos[] = { "Status-weather-clear-icon.png",
         "Status-weather-clouds-icon.png",
         "Status-weather-showers-scattered-day-icon.png",
         "Status-weather-showers-scattered-icon.png",
         "Status-weather-storm-night-icon.png"
 };

   public CapaIconos(WorldWindowGLCanvas wwd, LayerPanel panelCapas) {
      this.wwd = wwd;

      capa = new IconLayer();
      capa.setName("Iconos");
      ApplicationTemplate.insertBeforeCompass(wwd, capa);
      panelCapas.update(wwd);
   }

   public void pintaIconos() {
      for (String icono : iconos) {

         UserFacingIcon userIcon = new UserFacingIcon("files/forecast/"
               + icono, Position.fromDegrees(LATITUD_INICIAL + Math.random()
               * MAXIMO_DESPLAZAMIENTO - (MAXIMO_DESPLAZAMIENTO / 2.0),
               LONGITUD_INICIAL + Math.random() * MAXIMO_DESPLAZAMIENTO
                     - (MAXIMO_DESPLAZAMIENTO / 2.0)));
         userIcon.setSize(new Dimension(64, 64));
         userIcon.setValue(AVKey.DISPLAY_NAME, "el tiempo");
         capa.addIcon(userIcon);
      }
      wwd.redraw();
   }
}
