package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.event.PositionListener;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfacePolygon;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

public class DibujaPoligonos implements PositionListener, MouseMotionListener,
		MouseListener {

	private WorldWindowGLCanvas canvas;
	private RenderableLayer layer;
	private boolean activo = false;
	private List<Position> puntos = new LinkedList<Position>();
	private Position posicionActual = null;
	private SurfacePolygon poligono = null;
	ShapeAttributes normalAttributes;

	public DibujaPoligonos(WorldWindowGLCanvas canvas) {
		this.canvas = canvas;
		layer = new RenderableLayer();
		layer.setName("Poligonos");
		ApplicationTemplate.insertBeforeCompass(canvas, layer);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addPositionListener(this);

		normalAttributes = new BasicShapeAttributes();
		normalAttributes.setInteriorMaterial(Material.PINK);
		normalAttributes.setOutlineMaterial(Material.PINK);
		normalAttributes.setOutlineWidth(2);
		normalAttributes.setOutlineOpacity(0.7);
		normalAttributes.setInteriorOpacity(0.2);
		normalAttributes.setDrawInterior(true);
		normalAttributes.setDrawOutline(true);

	}

	@Override
	public void moved(PositionEvent event) {
		if (!isActivo()) {
			return;
		}
		if (event.getPosition()==null){
			return;
		}

		posicionActual = Position.fromDegrees(event.getPosition().getLatitude()
				.getDegrees(), event.getPosition().getLongitude().getDegrees(),
				0.0);
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
		if (activo) {
			poligono = new SurfacePolygon();
			puntos.clear();
			poligono.setAttributes(normalAttributes);
			
		}
	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent arg0) {
		if (isActivo()) {
			if (puntos.size() > 0) {
				puntos.set(puntos.size() - 1, posicionActual);
				poligono.setOuterBoundary(puntos);
				canvas.redraw();
			}
		}
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent event) {
		if (!isActivo()) {
			return;
		}
		if (event.getButton() == java.awt.event.MouseEvent.BUTTON3) {
			setActivo(false);
		} else {
			addPosition();
		}
	}

	private void addPosition() {
		// si es segundo click
		if (puntos.size() == 3  && puntos.get(0)==puntos.get(1)) {
				puntos.set(1, puntos.get(2));
		} else {
			puntos.add(posicionActual);
			// si es primer click
			if (puntos.size() == 1) {
				puntos.add(posicionActual);
				puntos.add(posicionActual);
				layer.addRenderable(poligono);
			} else {
				puntos.set(puntos.size()-1, posicionActual);
			}
		}
		poligono.setOuterBoundary(puntos);
		canvas.redraw();
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
