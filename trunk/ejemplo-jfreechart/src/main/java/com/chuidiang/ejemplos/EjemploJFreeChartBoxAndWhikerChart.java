package com.chuidiang.ejemplos;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.BoxAndWhiskerCalculator;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

/**
 * Ejemplo de grafico BoxAndWhiker.
 * 
 * @author Chuidiang
 * 
 */
public class EjemploJFreeChartBoxAndWhikerChart {
    private static final String BOX_AND_WHISKER_JPG = "BoxAndWhisker.jpg";

    public static void main(String[] args) {

        // Modelo de datos
        DefaultBoxAndWhiskerCategoryDataset boxAndWhisker = new DefaultBoxAndWhiskerCategoryDataset();

        // Serie uno de datos aleatorios
        LinkedList lista = new LinkedList();
        for (int i = 0; i < 6; i++)
            lista.add(Math.random());

        BoxAndWhiskerItem item = BoxAndWhiskerCalculator
                .calculateBoxAndWhiskerStatistics(lista);
        boxAndWhisker.add(item, "serie 1", "x1");

        // Serie dos de datos aleatorios
        lista = new LinkedList();
        for (int i = 0; i < 4; i++)
            lista.add(Math.random());

        item = BoxAndWhiskerCalculator.calculateBoxAndWhiskerStatistics(lista);
        boxAndWhisker.add(item, "serie 2", "x2");

        // Creación del gráfico
        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
                "Box and Whisker", "2 series de números", "Valores aleatorios",
                boxAndWhisker, true);
        ChartPanel panel = new ChartPanel(chart);

        JFrame ventana = new JFrame("El grafico");
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Se guarda un fichero jpg con el grafico
        try {
            String tmpDir = System.getProperty("java.io.tmpdir");
            ChartUtilities.saveChartAsJPEG(new File(tmpDir
                    + BOX_AND_WHISKER_JPG), chart, 500, 300);
            System.out.println("Salvada imagen en " + tmpDir
                    + BOX_AND_WHISKER_JPG);
        } catch (IOException e) {
            System.err.println("Error creando grafico.");
        }
    }
}
