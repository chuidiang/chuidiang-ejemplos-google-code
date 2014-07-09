package com.chuidiang.ejemplos.geotools;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.geotools.map.Layer;

public class PanelCapas extends JPanel{

	public PanelCapas(Layer[] layers) {
		super(new FlowLayout());
		
		for (Layer layer : layers) {
			JCheckBox checkBox = new JCheckBox(layer.getTitle(),layer.isVisible());
			checkBox.addActionListener(new LayerListener(layer));
			add(checkBox);
		}
	}

	
	class LayerListener implements ActionListener {
		private Layer layer;
		public LayerListener(Layer layer) {
			this.layer=layer;
		}
		@Override
		public void actionPerformed(ActionEvent event) {
			if (((JCheckBox)event.getSource()).isSelected()){
				layer.setVisible(true);
			} else {
				layer.setVisible(false);
			}
			
		}
	}
}
