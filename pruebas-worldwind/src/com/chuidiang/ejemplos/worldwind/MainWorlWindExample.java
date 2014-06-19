package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.symbology.TacticalGraphic;
import gov.nasa.worldwindx.examples.ApplicationTemplate;

import javax.swing.SwingUtilities;

public class MainWorlWindExample extends ApplicationTemplate {

	public static class AppFrame extends ApplicationTemplate.AppFrame {
		private static final int NUM_TRAZAS_POR_SENSORA = 10;
		private static final int NUM_SIMBOLOS_APP6 = 10;
		private static double[][] sensoras = { { 35.0, -1.0 }, { 38.0, -4.0 },
				{ 33 - 0, 0.0 } };
		private static TrazasSensora[] trazas;
		private RenderableLayer layer;
		private ElPopUp balloon = new ElPopUp();

		public AppFrame() {
			super(true, true, false);

			// this.getWwd().addSelectListener(new BasicDragger(this.getWwd()));
			this.getWwd().addSelectListener(new SelectListener() {

				@Override
				public void selected(SelectEvent event) {
					if (event.isLeftClick()) {
						balloon.showBalloon(event.getTopObject());
					} 
				}
			});

			addSymbolLayer();
			addShapeLayer();
			layer.addRenderable(balloon);
			// addTiffLayer();

			this.getLayerPanel().update(this.getWwd());

			actualizaDatosPeriodicamente();
		}

		private void addTiffLayer() {
			Layer layerTiff = FicheroTiff
					.leeFichero("d:/JAVIER/Downloads/craterlake-imagery-30m.tif");
			insertBeforeCompass(getWwd(), layerTiff);
		}

		private void addShapeLayer() {
			RenderableLayer shapeLayer = new RenderableLayer();
			shapeLayer.setName("Shapefile");
			ShapeFile
					.addShapefile(
							"C:/Users/JAVIER/Downloads/ne_10m_admin_0_countries/ne_10m_admin_0_countries.shp",
							shapeLayer);
			insertBeforeCompass(getWwd(), shapeLayer);
		}

		private void addSymbolLayer() {
			layer = new RenderableLayer();
			layer.setPickEnabled(true);

			Iterable<Renderable> simbolos = SimbolosAPP6
					.getSimbolos(NUM_SIMBOLOS_APP6);
			layer.addRenderables(simbolos);

			trazas = new TrazasSensora[sensoras.length];
			for (int i = 0; i < sensoras.length; i++) {
				trazas[i] = new TrazasSensora(sensoras[i][0], sensoras[i][1]);
				layer.addRenderables(trazas[i]
						.getTrazas(NUM_TRAZAS_POR_SENSORA));
			}

			TacticalGraphic tacticalGraphic = SimbolosAPP6.getTacticalGraphic();
			layer.addRenderable(tacticalGraphic);

			insertBeforeCompass(getWwd(), layer);
		}

		private void actualizaDatosPeriodicamente() {
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
									SimbolosAPP6.mueveSymbols();

									for (TrazasSensora traza : trazas) {
										traza.mueveTrazas();
									}

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
		Configuration.setValue(AVKey.INITIAL_LATITUDE, Integer.valueOf(36));
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, Integer.valueOf(-6));
		Configuration
				.setValue(AVKey.INITIAL_ALTITUDE, Integer.valueOf(1900000));
		ApplicationTemplate.start("NASA World Wind Tutorial - Simple Polygons",
				AppFrame.class); 
	}
}