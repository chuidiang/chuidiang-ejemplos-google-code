package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.VecBuffer;

import java.awt.Color;
import java.io.File;

public class ShapeFile {
	private static ShapeAttributes normalAttributes = null;

	public static void addShapefile(String nombreFichero,
			RenderableLayer layer, Color color) {

		File file = new File(nombreFichero);
		inicializaAttributos(color);
		Shapefile shapeFile = new Shapefile(file);
		while (shapeFile.hasNext()) {
			// Reading the shapefile current record and storing in a
			// temporary variable of the type ShapefileRecord
			ShapefileRecord record = shapeFile.nextRecord();
			String admin = record.getAttributes().getStringValue("ADMIN");

			for (int i = 0; i < record.getNumberOfParts(); i++) {
				String shapeType = record.getShapeType();
				VecBuffer vectorBuffer = record.getPointBuffer(i);

				if (shapeFile.SHAPE_POLYLINE.equals(shapeType)) {
					Polyline polyline = new Polyline();
					polyline.setColor(color);
					polyline.setFollowTerrain(true);
					polyline.setPositions(vectorBuffer.getPositions());
					polyline.setValue(AVKey.DISPLAY_NAME, admin);
					layer.addRenderable(polyline);
				} else if (shapeFile.SHAPE_POLYGON.equals(shapeType)) {
					Polygon polygon = new Polygon();
					polygon.setOuterBoundary(vectorBuffer.getPositions());
					// Setting polygon attributes
					polygon.setAttributes(normalAttributes);
					polygon.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
					polygon.setValue(AVKey.DISPLAY_NAME, admin);

					// Attaching the polygon to a layer
					layer.addRenderable(polygon);
				} else {
					System.out.println("Descartado " + shapeType);
				}
			}

		}

	}

	private static void inicializaAttributos(Color color) {
		normalAttributes = new BasicShapeAttributes();
		normalAttributes.setOutlineMaterial(new Material(color));
		normalAttributes.setInteriorMaterial(new Material(color));
		normalAttributes.setOutlineWidth(2);
		normalAttributes.setOutlineOpacity(1);
		normalAttributes.setInteriorOpacity(0.2);
		normalAttributes.setDrawInterior(true);
		normalAttributes.setDrawOutline(true);

	}
}