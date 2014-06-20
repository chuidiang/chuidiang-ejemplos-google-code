package com.chuidiang.ejemplos.worldwind;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.AnnotationFlowLayout;
import gov.nasa.worldwind.render.GlobeAnnotation;
import gov.nasa.worldwind.symbology.milstd2525.MilStd2525TacticalSymbol;
import gov.nasa.worldwindx.applications.worldwindow.core.ToolTipAnnotation;
import gov.nasa.worldwindx.examples.util.ImageAnnotation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ElPopUp extends GlobeAnnotation {

	private static final String IMAGEN_POPUP = "sonar-con-un-barco.jpg";
	private ToolTipAnnotation tooltip;
	private static MilStd2525TacticalSymbol objetoSeguido;

	public ElPopUp() {
		super("", Position.fromDegrees(0.0, 0.0));
		ImageAnnotation imagen = new ImageAnnotation(
				IMAGEN_POPUP, 100, 100);
		imagen.getAttributes().setImageScale(0.3);
		setLayout(new AnnotationFlowLayout(AVKey.VERTICAL));
		addChild(imagen);
		tooltip = new ToolTipAnnotation("");
		addChild(tooltip);
	}

	protected void showBalloon(Object objeto) {
		getAttributes().setVisible(false);
		if (objetoSeguido != null) {
			objetoSeguido
					.removePropertyChangeListener(AVKey.POSITION, listener);
		}

		if (!(objeto instanceof MilStd2525TacticalSymbol)) {
			return;
		}

		objetoSeguido = (MilStd2525TacticalSymbol) objeto;
		objetoSeguido.addPropertyChangeListener(AVKey.POSITION, listener);

		String htmlString = "<p>Soy el simbolo " + objetoSeguido.getIdentifier()
				+ "</p>" + "<p>y voy a " + objetoSeguido.getValue("velocidad")
				+ "</p>" + "<p>con rumbo " + objetoSeguido.getValue("direccion")
				+ "</p>";

		Position balloonPosition = objetoSeguido.getPosition();

		tooltip.setText(htmlString);
		moveTo(balloonPosition);
		getAttributes().setVisible(true);
	}

	protected PropertyChangeListener listener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent propertyEvent) {
			if (getAttributes().isVisible()) {
				moveTo((Position) propertyEvent.getNewValue());
				String htmlString = "<p>Soy el simbolo "
						+ objetoSeguido.getIdentifier() + "</p>"
						+ "<p>y voy a " + objetoSeguido.getValue("velocidad")
						+ "</p>" + "<p>con rumbo "
						+ objetoSeguido.getValue("direccion") + "</p>";
				tooltip.setText(htmlString);
			}
		}
	};

}
