package com.chuidiang.ejemplos;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Ejemplode AreaChart, modificando alguno de los colores por defecto del
 * grafico.
 * 
 * @author Chuidiang
 * 
 */
public class EjemploJFreeChartAreaChart {
    private static final String AREA_CHART_JPG = "Areachart.jpg";

    public static void main(String[] args) {

        // Los datos.
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(5, "Compras", "Enero");
        dataset.setValue(7, "Compras", "Febrero");
        dataset.setValue(9, "Compras", "Marzo");
        dataset.setValue(5, "Compras", "Abril");
        dataset.setValue(10, "Compras", "Mayo");

        // El grafico
        JFreeChart chart = ChartFactory.createAreaChart("Compras realizadas",
                "Compras realizadas, 2007", "Numero de Compras", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        // Cambiamos un par de los colores de defecto
        CategoryPlot catPlot = (CategoryPlot) chart.getPlot();
        AreaRenderer render = (AreaRenderer) catPlot.getRenderer();
        // un gradiente
        render.setSeriesPaint(0, new GradientPaint(0.0F, 0.0f, Color.RED,
                500.0f, 500.0f, Color.YELLOW));
        render.setSeriesOutlinePaint(0, Color.GREEN);

        // Y pintamos todo en la ventana
        ChartPanel panel = new ChartPanel(chart);

        JFrame ventana = new JFrame("El grafico");
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Modificacion de los datos en tiempo real.
        for (int i = 0; i < 10; i++) {
            dataset.setValue(i, "Compras", "Marzo");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Salvamos el gráfico en un fichero jpg
        try {
            String tmpDir = System.getProperty("java.io.tmpdir");
            ChartUtilities.saveChartAsJPEG(new File(tmpDir + AREA_CHART_JPG),
                    chart, 500, 300);
            System.out.println("Salvada imagen en " + tmpDir + AREA_CHART_JPG);
        } catch (IOException e) {
            System.err.println("Error creando grafico.");
        }
    }
}
