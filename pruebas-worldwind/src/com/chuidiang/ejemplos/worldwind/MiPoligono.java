package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;

import java.util.ArrayList;

public class MiPoligono {
	private Polygon poly;
	public MiPoligono() {
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
	}
	
	public Polygon getPolygon(){
		return poly;
	}
}
