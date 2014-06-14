package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.symbology.BasicTacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.TacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.milstd2525.MilStd2525TacticalSymbol;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class PolygonExample extends ApplicationTemplate {
	public static class AppFrame extends ApplicationTemplate.AppFrame {
		private Polygon poly;
		private RenderableLayer layer;

		public AppFrame() {
			super(true, true, false);

			// Enable shape dragging, if you want.
			// this.getWwd().addSelectListener(new BasicDragger(this.getWwd()));

			layer = new RenderableLayer();

			// Set the basic attributes of your polygon
			ShapeAttributes normalAttributes = new BasicShapeAttributes();
			normalAttributes.setInteriorMaterial(Material.YELLOW);
			normalAttributes.setOutlineWidth(2);
			normalAttributes.setOutlineOpacity(0.5);
			normalAttributes.setDrawInterior(true);
			normalAttributes.setDrawOutline(true);

			// Set the coordinates (in degrees) to draw your polygon
			// To radians just change the method the class Position
			// to fromRadians().
			ArrayList positions = new ArrayList();
			positions.add(Position.fromDegrees(52, 10, 5e4));
			positions.add(Position.fromDegrees(55, 11, 5e4));
			positions.add(Position.fromDegrees(52, 14, 5e4));

			poly = new Polygon(positions);

			poly.setAttributes(normalAttributes);
			// Tooltip text of the polygon
			poly.setValue(AVKey.DISPLAY_NAME, "My first polygon");
			// Add the just created polygon to a renderable layer
			layer.addRenderable(poly);

			positions = new ArrayList();
			positions.add(Position.fromDegrees(0, 0));
			positions.add(new Position(Position.greatCircleEndPosition(
					Position.fromDegrees(0, 0), 45.0, 180.0), 0));

			Polyline linea = new Polyline(positions);
			linea.setColor(Color.RED);
			linea.setFilled(true);
			linea.setFollowTerrain(true);
			layer.addRenderable(linea);

			Iterable<Renderable> simbolos = SimbolosAPP6.getSimbolos(1000);
			layer.addRenderables(simbolos);

			// getWwd().getModel().setLayers(new LayerList(new Layer[]
			// {layer}));
			// Add the layer to the model.
			insertBeforeCompass(getWwd(), layer);
			// Update layer panel
			this.getLayerPanel().update(this.getWwd());

			new Thread() {
				private double latitude;

				public void run() {
					latitude = 52;
					while (true) {
						try {
							Thread.sleep(100);

							SwingUtilities.invokeLater(new Runnable() {

								@Override
								public void run() {
									Position p = (Position) ((ArrayList) (poly
											.getBoundaries().get(0))).get(1);
									ArrayList positions = new ArrayList();
									positions.add(Position.fromDegrees(
											latitude + 0.1, 10, 5e4));
									positions.add(Position.fromDegrees(55, 11,
											5e4));
									positions.add(Position.fromDegrees(52, 14,
											5e4));
									poly.setOuterBoundary(positions);
									// poly.moveTo(Position.fromDegrees(latitude
									// + 0.1, 10, 5e4));
									latitude = latitude + 0.1;
									// layer.firePropertyChange(AVKey.LAYER,
									// null, AppFrame.this.getWwd());

									SimbolosAPP6.mueveSymbols();

									AppFrame.this.getWwd().redraw();

								}
							});
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
	}

	public static void main(String[] args) {
		// Set the initial configurations of your NASA World Wind App
		// Altitute, logitude and latitute, and window caption.
		Configuration.setValue(AVKey.INITIAL_LATITUDE, Integer.valueOf(54));
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, Integer.valueOf(13));
		Configuration
				.setValue(AVKey.INITIAL_ALTITUDE, Integer.valueOf(1900000));
		ApplicationTemplate.start("NASA World Wind Tutorial - Simple Polygons",
				AppFrame.class);
	}
}