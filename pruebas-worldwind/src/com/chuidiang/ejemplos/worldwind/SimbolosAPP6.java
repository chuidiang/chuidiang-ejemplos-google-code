package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.symbology.BasicTacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.TacticalSymbolAttributes;
import gov.nasa.worldwind.symbology.milstd2525.MilStd2525TacticalSymbol;

import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

public class SimbolosAPP6 {
	private static List<Renderable> listaSimbolos;
	private static double latitudInicial = 36.0;
	private static double longitudInicial = 36.0;
	private static TacticalSymbolAttributes normalAttributes = null;

	public static Iterable<Renderable> getSimbolos(int numSimbolos) {
		listaSimbolos = new LinkedList<>();

		if (null == normalAttributes) {
			inicializaNormalAttributes();
		}

		for (int i = 0; i < numSimbolos; i++) {
			MilStd2525TacticalSymbol simbolo = new MilStd2525TacticalSymbol(
					"SFAPMFQM------A", Position.fromDegrees(latitudInicial
							+ Math.random(), longitudInicial + Math.random()));
			listaSimbolos.add(simbolo);
			TacticalSymbolAttributes att = new BasicTacticalSymbolAttributes();
			att.setScale(0.3);
			simbolo.setAttributes(normalAttributes);
			simbolo.setValue("velocidad", Math.random());
			simbolo.setValue("direccion", Math.random() * 360);
		}

		return listaSimbolos;
	}

	public static void mueveSymbols() {
		for (Renderable simbolo : listaSimbolos) {
			MilStd2525TacticalSymbol app6 = (MilStd2525TacticalSymbol) simbolo;
			double velocidad = (Double) app6.getValue("velocidad");
			double direccion = (Double) app6.getValue("direccion");
			Position posicionActual = app6.getPosition();
			Position nuevaPosicion = new Position(
					Position.greatCircleEndPosition(posicionActual,
							direccion, velocidad*0.001), 0);
			app6.setValue("direccion", direccion+Math.random()/100.0);
			app6.moveTo(nuevaPosicion);
		}

	}

	private static void inicializaNormalAttributes() {
		normalAttributes = new BasicTacticalSymbolAttributes();
		normalAttributes.setScale(0.2);
		normalAttributes.setOpacity(1.0);
		normalAttributes.setTextModifierFont(Font.decode("Arial-Bold-12"));
		normalAttributes.setTextModifierMaterial(Material.RED);
	}
}
