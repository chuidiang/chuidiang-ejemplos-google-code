package com.chuidiang.ejemplos.geotools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.collection.CollectionFeatureSource;
import org.geotools.data.memory.MemoryFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;
import org.geotools.swing.action.PanAction;
import org.geotools.swing.action.ResetAction;
import org.geotools.swing.action.ZoomInAction;
import org.geotools.swing.action.ZoomOutAction;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;

/**
 * Prueba de Geotools. 
 * Necesitas los jar de geotools gt-swing-10.7.jar,
 * gt-shapefile-10.7.jar y gt-epsg-hsql-10.7.jar con todo
 * de lo que dependen estas.
 * 
 * En el path ficheros/ne_10m_admin_0_countries hay que poner
 * los ficheros de mapa que se pueden bajar de 
 * http://www.naturalearthdata.com/downloads/10m-cultural-vectors/10m-admin-0-countries/
 * Aunque el codigo solo menciona el shapefile, hay que colocar en ese directorio todos
 * los demas ficheros que vienen en el zip.
 * 
 * @author chuidiang
 *
 */
public class SimpleGeotoolsMain {

   public static void main(String[] args) throws IOException {
      // display a data store file chooser dialog for shapefiles
      // Puedes descargar el fichero de
      // http://www.naturalearthdata.com/downloads/10m-cultural-vectors/10m-admin-0-countries/
      File file = new File(
            "files/ne_10m_admin_0_countries/ne_10m_admin_0_countries.shp");

      FileDataStore store = FileDataStoreFinder.getDataStore(file);
      SimpleFeatureSource featureSource = store.getFeatureSource();

      Style style = SLD.createSimpleStyle(featureSource.getSchema());
      style=SLD.createPolygonStyle(Color.BLACK, Color.YELLOW, 0.2f);
      
      MapContent map = new MapContent();

      Layer layer = new FeatureLayer(featureSource, style);
      layer.setTitle("mundo");
      map.addLayer(layer);

      final JFrame frame = new JFrame("GIS");
      JMapPane mapPane = new JMapPane(map);
      frame.getContentPane().add(mapPane);
      frame.setSize(500,500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      SwingUtilities.invokeLater(new Runnable() {
         
         @Override
         public void run() {
            frame.setVisible(true);
            
         }
      });
      
      MouseWheel.initZoomWithMouseWheel(mapPane);

     }
}
