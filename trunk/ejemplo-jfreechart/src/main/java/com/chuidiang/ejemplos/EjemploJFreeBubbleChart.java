package com.chuidiang.ejemplos;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYZDataset;

/**
 * Ejemplo de BubbleChart. El gráfico pinta bolas de colores.
 * 
 * @author Chuidiang
 * 
 */
public class EjemploJFreeBubbleChart {
    private static final String BUBBLE_CHART_JPG = "BubbleChart.jpg";

    public static void main(String[] args) {
        DefaultXYZDataset dataSet = new DefaultXYZDataset();
        // Un array con tres arrays dentro. El primer array son las x
        // de los centros de las burbujas, el segundo array son las y
        // de los centros de las burbujas y el tercer array son los
        // diámetros de las burbujas. Los tres arrays deben tener el
        // mismo número de elementos
        dataSet.addSeries("serie 1", new double[][] { { 0, 2 }, // x de los
                // centros
                { 0, 1 }, // y de los centros
                { 1, 1 } // diámetros
                });
        dataSet.addSeries("serie 2", new double[][] { { 1, 3 }, // x de los
                // centros
                { 1, 4 }, // y de los centros
                { 0.4, 0.6 } // diámetros
                });

        final JFreeChart chart = ChartFactory.createBubbleChart(
                "Título:Bubble Chart", "etiqueta eje x", "etiqueta eje y",
                dataSet, PlotOrientation.VERTICAL, true, true, true);
        ChartPanel panel = new ChartPanel(chart);

        JFrame ventana = new JFrame("El grafico");
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        // Salvamos en fichero jpg.
        try {
            String tmpDir = System.getProperty("java.io.tmpdir");
            ChartUtilities.saveChartAsJPEG(new File(tmpDir + BUBBLE_CHART_JPG),
                    chart, 500, 300);
            System.out
                    .println("Salvada imagen en " + tmpDir + BUBBLE_CHART_JPG);
        } catch (IOException e) {
            System.err.println("Error creando grafico.");
        }
    }
}
