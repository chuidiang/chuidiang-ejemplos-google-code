package org.chuidiang.chuwiki.worldwind;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygon;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.LayerPanel;

import java.util.LinkedList;
import java.util.List;

public class CapaPoligonos {
   private WorldWindowGLCanvas wwd;
   
   private RenderableLayer capa;

   public CapaPoligonos(WorldWindowGLCanvas wwd, LayerPanel panelCapas) {
      this.wwd = wwd;
      
      capa = new RenderableLayer();
      capa.setName("Poligonos");
      ApplicationTemplate.insertBeforeCompass(wwd, capa);
      panelCapas.update(wwd);
      
      // Forma alterntiva de añadir la capa.
      // canvas.getModel().getLayers().add(capa);
   }
   
   public void pintaPoligono(){
      List<Position> vertices = new LinkedList<>();
      vertices.add(Position.fromDegrees(40.0, -3.0, 700.0));
      vertices.add(Position.fromDegrees(40.1, -3.0, 700.0));
      vertices.add(Position.fromDegrees(40.1, -3.1, 700.0));
      vertices.add(Position.fromDegrees(40.0, -3.1, 700.0));
      
      Polygon poligono = new Polygon(vertices);
      // Los vertices podrian asignarse tambien con poligono.setOuterBoundary()
      // en vez de en el constructor.
      
      // Alturas relativas a nivel del terreno
      // poligono.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
      
      ShapeAttributes atributos = new BasicShapeAttributes();
      atributos.setOutlineMaterial(Material.RED);
      atributos.setInteriorMaterial(Material.RED);
      atributos.setInteriorOpacity(0.2);
      poligono.setAttributes(atributos);
      
      capa.addRenderable(poligono);
      wwd.redraw();
   }
   
   public void pintaPoligonoExtruido(){
      List<Position> vertices = new LinkedList<>();
      vertices.add(Position.fromDegrees(40.2, -3.0, 600.0));
      vertices.add(Position.fromDegrees(40.3, -3.0, 600.0));
      vertices.add(Position.fromDegrees(40.3, -3.1, 600.0));
      vertices.add(Position.fromDegrees(40.2, -3.1, 600.0));
      
      ExtrudedPolygon poligono = new ExtrudedPolygon(vertices,700.0);
      // Los vertices podrian asignarse tambien con poligono.setOuterBoundary()
      // en vez de en el constructor.
      // La altura puede asignarse con poligono.setHeight()
      
      ShapeAttributes atributos = new BasicShapeAttributes();
      atributos.setOutlineMaterial(Material.RED);
      atributos.setDrawInterior(false);
      poligono.setAttributes(atributos);
      
      capa.addRenderable(poligono);
      wwd.redraw();
   }
   
   public void pintaPoligonoSuperficie(){
      List<Position> vertices = new LinkedList<>();
      vertices.add(Position.fromDegrees(40.0, -3.2));
      vertices.add(Position.fromDegrees(40.1, -3.2));
      vertices.add(Position.fromDegrees(40.1, -3.3));
      vertices.add(Position.fromDegrees(40.0, -3.3));
      
      SurfacePolygon poligono = new SurfacePolygon(vertices);
      // Los vertices podrian asignarse tambien con poligono.setOuterBoundary()
      // en vez de en el constructor.
      
      ShapeAttributes atributos = new BasicShapeAttributes();
      atributos.setOutlineMaterial(Material.RED);
      atributos.setInteriorMaterial(Material.RED);
      atributos.setInteriorOpacity(0.2);
      poligono.setAttributes(atributos);
      
      capa.addRenderable(poligono);
      wwd.redraw();
      
   }

}
