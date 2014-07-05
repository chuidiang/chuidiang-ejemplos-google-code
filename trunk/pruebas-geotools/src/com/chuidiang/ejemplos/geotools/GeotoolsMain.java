package com.chuidiang.ejemplos.geotools;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapPane;
import org.geotools.swing.SingleLayerMapContent;
import org.geotools.swing.action.PanAction;
import org.geotools.swing.action.ResetAction;
import org.geotools.swing.action.ZoomInAction;
import org.geotools.swing.action.ZoomOutAction;

public class GeotoolsMain {

   public static void main(String[] args) throws IOException {
      // display a data store file chooser dialog for shapefiles
      // Puedes descargar el fichero de http://www.naturalearthdata.com/downloads/10m-cultural-vectors/10m-admin-0-countries/
      File file = new File(
            "files/ne_10m_admin_0_countries/ne_10m_admin_0_countries.shp");

      FileDataStore store = FileDataStoreFinder.getDataStore(file);
      SimpleFeatureSource featureSource = store.getFeatureSource();

      // Create a map content and add our shapefile to it
      MapContent map = new MapContent();
      map.setTitle("Quickstart");

      Style style = SLD.createSimpleStyle(featureSource.getSchema());

      Layer layer = new FeatureLayer(featureSource, style);
      map.addLayer(layer);
      
      // La siguiente linea dibuja un JFrame con el mapa y toda una
      // botoneria por defecto. Si llamamos a esta linea, el resto
      // de código sobra.
      // JMapFrame.showMap(map);
      
      
      // Como no hemos llamado a la linea anterior, vamos a construir el 
      // JFrame y todos los botones.
      JMapPane pane = new JMapPane(new SingleLayerMapContent(layer));
      
      JToolBar toolBar = new JToolBar();
      toolBar.add(new ZoomInAction(pane));
      toolBar.add(new ZoomOutAction(pane));
      toolBar.add(new PanAction(pane));
      toolBar.add(new ResetAction(pane));
      
      JFrame ventana = new JFrame("mapa");
      ventana.getContentPane().add(pane);
      ventana.getContentPane().add(toolBar,BorderLayout.NORTH);
      
      ventana.setSize(500, 500);
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana.setVisible(true);

   }

}
