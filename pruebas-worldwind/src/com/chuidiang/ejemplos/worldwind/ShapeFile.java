package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.VecBuffer;

import java.io.File;

public class ShapeFile {
	private static ShapeAttributes normalAttributes = null;

	public static void addShapefile(String nombreFichero, RenderableLayer layer) {
		// File file = new
		// File("C:/Users/JAVIER/Downloads/ne_10m_admin_0_countries/ne_10m_admin_0_countries.shp");
		File file = new File(nombreFichero);
		if (null == normalAttributes) {
			inicializaAttributos();
		}
		Shapefile shapeFile = new Shapefile(file);
		while (shapeFile.hasNext()) {
			// Reading the shapefile current record and storing in a
			// temporary variable of the type ShapefileRecord
			ShapefileRecord record = shapeFile.nextRecord();
			String admin = record.getAttributes().getStringValue("ADMIN");

			for (int i = 0; i < record.getNumberOfParts(); i++) {
				VecBuffer vectorBuffer = record.getPointBuffer(i);
				// Setting height
				Double height = 500.50;
				// Creating an instance of ExtrudedPolygon
				Polygon polygon = new Polygon();
				// Setting the polygon outer boundary based on
				// the current shapefile record

				polygon.setOuterBoundary(vectorBuffer.getPositions());

				// Setting polygon attributes
				polygon.setAttributes(normalAttributes);
				polygon.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
				polygon.setValue(AVKey.DISPLAY_NAME, admin);

				// Attaching the polygon to a layer
				layer.addRenderable(polygon);
			}

		}

	}

	private static void inicializaAttributos() {
		normalAttributes = new BasicShapeAttributes();
		normalAttributes.setOutlineMaterial(Material.YELLOW);
		normalAttributes.setInteriorMaterial(Material.YELLOW);
		normalAttributes.setOutlineWidth(2);
		normalAttributes.setOutlineOpacity(1);
		normalAttributes.setInteriorOpacity(0.2);
		normalAttributes.setDrawInterior(true);
		normalAttributes.setDrawOutline(true);

	}
}
