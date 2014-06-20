package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.Configuration;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.ViewInputAttributes;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.globes.EarthFlat;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.layers.WorldMapLayer;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.symbology.TacticalGraphic;
import gov.nasa.worldwind.view.orbit.FlatOrbitView;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import gov.nasa.worldwindx.examples.ClickAndGoSelectListener;
import gov.nasa.worldwindx.examples.LayerPanel;
import gov.nasa.worldwindx.examples.util.HighlightController;
import gov.nasa.worldwindx.examples.util.ToolTipController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class MainWorlWindExample {
	private static final String SHAPE_FILE = "D:/MAPAS/ne_10m_admin_0_countries/ne_10m_admin_0_countries.shp";
	private static final int NUM_TRAZAS_POR_SENSORA = 10;
	private static final int NUM_SIMBOLOS_APP6 = 10;
	private static double[][] sensoras = { { 35.0, -1.0 }, { 38.0, -4.0 },
			{ 33 - 0, 0.0 } };
	private static TrazasSensora[] trazas;
	private RenderableLayer layer;
	private ElPopUp balloon = new ElPopUp();
	private WorldWindowGLCanvas canvas;
	private DibujaPoligonos dibujaPoligonos;
	private LayerPanel panelCapas;

	public MainWorlWindExample() {
		final JFrame ventana = new JFrame("Ejemplo WorlWind");

		creaCanvas();

		ventana.getContentPane().add(canvas);

		panelCapas = new LayerPanel(canvas);
		ventana.getContentPane().add(panelCapas, BorderLayout.WEST);

		// this.getWwd().addSelectListener(new BasicDragger(this.getWwd()));
		canvas.addSelectListener(new SelectListener() {
			@Override
			public void selected(SelectEvent event) {
				if (event.isLeftClick()) {
					balloon.showBalloon(event.getTopObject());
				}
			}
		});

		addSymbolLayer();
		addShapeLayers();
		layer.addRenderable(balloon);
		dibujaPoligonos = new DibujaPoligonos(canvas);
		
		addTiffLayer();

		JToolBar toolbar = new JToolBar();

		JButton dibuja = new JButton("poligono");
		toolbar.add(dibuja);
		dibuja.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dibujaPoligonos.setActivo(true);
			}
		});
		ventana.getContentPane().add(toolbar, BorderLayout.NORTH);

		panelCapas.update(canvas);

		ventana.setSize(1200, 900);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ventana.setVisible(true);
			}
		});

		actualizaDatosPeriodicamente();

	}

	private void creaCanvas() {
		canvas = new WorldWindowGLCanvas();
		canvas.addSelectListener(new ClickAndGoSelectListener(canvas,
				WorldMapLayer.class));
		new ToolTipController(canvas, AVKey.DISPLAY_NAME, null);
		new HighlightController(canvas, SelectEvent.ROLLOVER);
		canvas.setModel(new BasicModel());
		ViewInputAttributes attrs = canvas.getView().getViewInputHandler()
				.getAttributes();
		attrs.getActionMap(ViewInputAttributes.DEVICE_MOUSE)
				.getActionAttributes(ViewInputAttributes.VIEW_MOVE_TO)
				.setMouseActionListener(null);
//		attrs.getActionMap(ViewInputAttributes.DEVICE_MOUSE)
//		.getActionAttributes(ViewInputAttributes.VIEW_ROTATE)
//		.setMouseActionListener(null);
	}

	private void addTiffLayer() {
		
		new Thread() {
			public void run() {
				long time = System.currentTimeMillis();
				Layer layerTiff = FicheroTiff
						.leeFichero("KazajstanOsat_tiled.tif");
				System.out.println("tarda "+(System.currentTimeMillis()-time));
				ApplicationTemplate.insertBeforeCompass(canvas, layerTiff);
				panelCapas.update(canvas);
			}
		}.start();
		
	}

	private void addShapeLayers() {
		RenderableLayer shapeLayer = new RenderableLayer();
		shapeLayer.setName("Shapefile");
		ShapeFile.addShapefile(SHAPE_FILE, shapeLayer, Color.YELLOW);
		ApplicationTemplate.insertBeforeCompass(canvas, shapeLayer);
		
		RenderableLayer layerMapinfo = new RenderableLayer();
		layerMapinfo.setName("MapInfo como Shape");
		ShapeFile.addShapefile("d:/MAPAS/PRUEBAS/spain.shp", layerMapinfo,
				Color.YELLOW);
		ShapeFile.addShapefile("d:/MAPAS/PRUEBAS/es_hiway.shp", layerMapinfo,
				Color.RED);
		ShapeFile.addShapefile("d:/MAPAS/PRUEBAS/es_water.shp", layerMapinfo,
				Color.BLUE);
		ApplicationTemplate.insertBeforeCompass(canvas, layerMapinfo);
		
	}

	private void addSymbolLayer() {
		layer = new RenderableLayer();
		layer.setName("simbologia app6");
		layer.setPickEnabled(true);

		Iterable<Renderable> simbolos = SimbolosAPP6
				.getSimbolos(NUM_SIMBOLOS_APP6);
		layer.addRenderables(simbolos);

		trazas = new TrazasSensora[sensoras.length];
		for (int i = 0; i < sensoras.length; i++) {
			trazas[i] = new TrazasSensora(sensoras[i][0], sensoras[i][1]);
			layer.addRenderables(trazas[i].getTrazas(NUM_TRAZAS_POR_SENSORA));
		}

		TacticalGraphic tacticalGraphic = SimbolosAPP6.getTacticalGraphic();
		layer.addRenderable(tacticalGraphic);

		ApplicationTemplate.insertBeforeCompass(canvas, layer);
	}

	private void actualizaDatosPeriodicamente() {
		new Thread() {
			public void run() {
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

								canvas.redraw();
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public static void main(String[] args) {
		System.setProperty("java.library.path", "D:/LIBRERIAS/worldwind/worldwind/lib-external/gdal");
		System.setProperty("java.net.useSystemProxies", "true");
		// Set the initial configurations of your NASA World Wind App
		// Altitute, logitude and latitute, and window caption.
		Configuration.setValue(AVKey.INITIAL_LATITUDE, Integer.valueOf(36));
		Configuration.setValue(AVKey.INITIAL_LONGITUDE, Integer.valueOf(-6));
		Configuration
				.setValue(AVKey.INITIAL_ALTITUDE, Integer.valueOf(1900000));
//        Configuration.setValue(AVKey.GLOBE_CLASS_NAME, EarthFlat.class.getName());
//        Configuration.setValue(AVKey.VIEW_CLASS_NAME, FlatOrbitView.class.getName());

		new MainWorlWindExample();
	}
}