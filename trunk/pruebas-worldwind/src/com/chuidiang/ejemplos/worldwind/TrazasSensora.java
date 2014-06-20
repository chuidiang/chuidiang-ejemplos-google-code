package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polyline;
import gov.nasa.worldwind.render.Renderable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrazasSensora {
	private List<Renderable> listaTrazas;
	private double latitudInicial = 36.0;
	private double longitudInicial = 36.0;
	private Color[] colores = { Color.RED, Color.CYAN, Color.YELLOW };

	public TrazasSensora(double latitud, double longitud) {
		latitudInicial = latitud;
		longitudInicial = longitud;
	}

	public Iterable<Renderable> getTrazas(int numTrazas) {
		listaTrazas = new LinkedList<>();
		for (int i = 0; i < numTrazas; i++) {
			List<Position> positions = new ArrayList<>();
			double azimuth = Math.random() * 360;
			positions
					.add(Position.fromDegrees(latitudInicial, longitudInicial));
			positions.add(new Position(Position.greatCircleEndPosition(
					Position.fromDegrees(latitudInicial, longitudInicial),
					Angle.fromDegrees(azimuth), Angle.fromDegrees(2.0)), 0));
			Polyline linea = new Polyline(positions);
			linea.setColor(colores[i % (colores.length)]);
			linea.setFollowTerrain(true);
			linea.setValue("azimuth", azimuth);
			linea.setLineWidth(100.0);
			linea.setValue(AVKey.DISPLAY_NAME, Double.toString(azimuth));
			listaTrazas.add(linea);
		}

		return listaTrazas;
	}

	public void mueveTrazas() {
		for (Renderable renderable : listaTrazas) {
			Polyline traza = (Polyline) renderable;
			double azimuth = (Double) traza.getValue("azimuth");
			azimuth = azimuth + Math.random() / 10.0;
			List<Position> positions = new ArrayList<>();
			positions
					.add(Position.fromDegrees(latitudInicial, longitudInicial));
			positions.add(new Position(Position.greatCircleEndPosition(
					Position.fromDegrees(latitudInicial, longitudInicial),
					Angle.fromDegrees(azimuth), Angle.fromDegrees(2.0)), 0));
			traza.setPositions(positions);
			traza.setValue("azimuth",azimuth);
		}

	}

}
