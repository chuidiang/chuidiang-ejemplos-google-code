package com.chuidiang.ejemplos.geotools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JToolBar;

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
public class GeotoolsMain {

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
      
      CollectionFeatureSource propias = createFeatureType();
      Layer layer2 = new FeatureLayer(propias, SLD.createPolygonStyle(Color.RED, Color.GREEN, 0.2f));
      layer2.setTitle("rectangulo");
      map.addLayer(layer2);

      // La siguiente linea dibuja un JFrame con el mapa y toda una
      // botoneria por defecto. Si llamamos a esta linea, el resto
      // de código sobra.
      // JMapFrame.showMap(map);

      // Como no hemos llamado a la linea anterior, vamos a construir el
      // JFrame y todos los botones.
      JMapPane pane = new JMapPane(map);

      JToolBar toolBar = new JToolBar();
      toolBar.add(new ZoomInAction(pane));
      toolBar.add(new ZoomOutAction(pane));
      toolBar.add(new PanAction(pane));
      toolBar.add(new ResetAction(pane));
      
      PanelCapas panelCapas = new PanelCapas(new Layer[]{layer,layer2});
      

      JFrame ventana = new JFrame("mapa");
      ventana.getContentPane().add(pane);
      ventana.getContentPane().add(toolBar, BorderLayout.NORTH);
      ventana.getContentPane().add(panelCapas, BorderLayout.SOUTH);

      ventana.setSize(500, 500);
      ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      ventana.setVisible(true);

   }

   private static CollectionFeatureSource createFeatureType() {

      SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
      builder.setName("Location");
      builder.setSRS("EPSG:4326"); // <- Coordinate reference system

      // add attributes in order
      builder.add("the_geom", Polygon.class);
      builder.length(15).add("Name", String.class); // <- 15 chars width for
                                                    // name field
      builder.add("number", Integer.class);

      // build the type
      final SimpleFeatureType LOCATION = builder.buildFeatureType();

      SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(LOCATION);
      GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

      Coordinate[] coordinates = new Coordinate[] { new Coordinate(1.0, 1.0),
            new Coordinate(1.0, 2.0), new Coordinate(2.0, 2.0),
            new Coordinate(2.0, 1.0), new Coordinate(1.0, 1.0) };

      Polygon poligono = geometryFactory.createPolygon(coordinates);

      featureBuilder.add(poligono);
      featureBuilder.add("el nombre");
      featureBuilder.add(22);

      MemoryFeatureCollection listFeatures = new MemoryFeatureCollection(
            LOCATION);
      listFeatures.add(featureBuilder.buildFeature(null));
      CollectionFeatureSource featureSource = new CollectionFeatureSource(
            listFeatures);
      return featureSource;
   }

}
