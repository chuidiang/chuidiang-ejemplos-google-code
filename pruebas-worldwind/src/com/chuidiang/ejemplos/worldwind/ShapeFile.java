package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.VecBuffer;

import java.io.File;

public class ShapeFile {
	private static ShapeAttributes normalAttributes = null;

	public static void addShapefile(String nombreFichero, RenderableLayer layer) {
		// File file = new
		// File("C:/Users/JAVIER/Downloads/ne_10m_admin_0_countries/ne_10m_admin_0_countries.shp");
		File file = new File("d:/JAVIER/Downloads/ESP_adm/ESP_adm1.shp");
		if (null == normalAttributes) {
			inicializaAttributos();
		}
		Shapefile shapeFile = new Shapefile(file);
		while (shapeFile.hasNext()) {
			// Reading the shapefile current record and storing in a
			// temporary variable of the type ShapefileRecord
			ShapefileRecord record = shapeFile.nextRecord();
			VecBuffer vectorBuffer = record.getPointBuffer(0);
			// Setting height
			Double height = 500.50;
			// Creating an instance of ExtrudedPolygon
			ExtrudedPolygon polygon = new ExtrudedPolygon();
			// Setting the polygon outer boundary based on
			// the current shapefile record
			polygon.setOuterBoundary(vectorBuffer.getLocations(), height);

			// Setting polygon attributes
			polygon.setAttributes(normalAttributes);
			// Attaching the polygon to a layer
			layer.addRenderable(polygon);
			
		}

	}

	private static void inicializaAttributos() {
		normalAttributes = new BasicShapeAttributes();
		normalAttributes.setInteriorMaterial(Material.YELLOW);
		normalAttributes.setOutlineWidth(2);
		normalAttributes.setOutlineOpacity(0.5);
		normalAttributes.setDrawInterior(true);
		normalAttributes.setDrawOutline(true);

	}
}
