package com.chuidiang.ejemplos;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultWindDataset;

/**
 * Ejemplo de WindPlot
 * 
 * @author Chuidiang
 * 
 */
public class EjemploJFreeWindPlot {
    private static final String WIND_PLOT_JPG = "Windplot.jpg";

    public static void main(String[] args) {
        // Creacion de los datos
        String nombres[] = { "serie 1", "serie 2", "serie 3" };

        Object[][][] valores = new Object[nombres.length][4][3];
        long actual = System.currentTimeMillis();
        for (int i = 0; i < nombres.length; i++) {
            for (int j = 0; j < 4; j++) {
                // Fecha del dato
                valores[i][j][0] = new Date(actual + j * 3600000 * 24);
                // Dirección, de 1 a 12, como el reloj
                valores[i][j][1] = new Double(5.0 + j + i);
                // Fuerza, de 1 a 12.
                valores[i][j][2] = new Double(4.0 + j + i);
            }
            actual += 3600000 * 24 * 5;
        }

        DefaultWindDataset dataset = new DefaultWindDataset(nombres, valores);

        // Creacion del gráfico.
        JFreeChart chart = ChartFactory.createWindPlot("Wind Plot", "Fecha",
                "Valores verticales", dataset, true, true, true);

        // Creacion y visualizacion de la ventana.
        ChartPanel panel = new ChartPanel(chart);

        JFrame ventana = new JFrame("El grafico");
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se salva el grafico en un fichero jpg
        try {
            String tmpDir = System.getProperty("java.io.tmpdir");
            ChartUtilities.saveChartAsJPEG(new File(tmpDir + WIND_PLOT_JPG),
                    chart, 500, 300);
            System.out.println("Salvada imagen en " + tmpDir + WIND_PLOT_JPG);
        } catch (IOException e) {
            System.err.println("Error creando grafico.");
        }
    }
}
