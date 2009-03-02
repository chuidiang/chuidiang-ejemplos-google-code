package com.chuidiang.ejemplos;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

/**
 * Ejemplo de grafico con histograma.
 * 
 * @author Chuidiang
 * 
 */
public class EjemploJFreeHistogram {
    private static final String HISTOGRAM_JPG = "HistogramChart.jpg";

    public static void main(String[] args) {
        // Los datos.
        HistogramDataset dataset = new HistogramDataset();
        double[] x = new double[100];
        for (int i = 0; i < 100; i++)
            x[i] = Math.random();
        dataset.addSeries("Series 1", x, 20);

        // Creacion del grafico
        JFreeChart chart = ChartFactory.createHistogram("Título:Histogram",
                "etiqueta eje x", "etiqueta eje y", dataset,
                PlotOrientation.VERTICAL, true, true, true);

        // Visualizacion de la ventana.
        final ChartPanel panel = new ChartPanel(chart);
        JFrame ventana = new JFrame("El grafico");
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        // Se guarda el grafico en un fichero jpg
        try {
            String tmpDir = System.getProperty("java.io.tmpdir");
            ChartUtilities.saveChartAsJPEG(new File(tmpDir + HISTOGRAM_JPG),
                    chart, 500, 300);
            System.out.println("Salvada imagen en " + tmpDir + HISTOGRAM_JPG);
        } catch (IOException e) {
            System.err.println("Error creando grafico.");
        }
    }
}
