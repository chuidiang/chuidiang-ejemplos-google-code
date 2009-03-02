package com.chuidiang.ejemplos;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.TableOrder;

/**
 * Ejemplo con JFrechart. Se construye y muestra en una ventana un gráfico de
 * barras para comparar las visitas de dos sitios web. También se salva el
 * gráfico en un fichero jpg
 * 
 * @author Chuidiang
 */
public class EjemploJFreeCategoryDataset {
    /** Sitio web 1 */
    private static final String SITIO_2 = "www.sitio1.com";
    /** Sitio web 2 */
    private static final String SITIO_1 = "www.sitio2.com";
    /** Nombre del fichero para salvar el gráfico */
    private static final String BARCHART_JPG = "chart.jpg";

    /**
     * Instancia esta clase
     * 
     * @param args
     */
    public static void main(String[] args) {
        new EjemploJFreeCategoryDataset();
    }

    /**
     * Rellena un modelo de datos y pinta una ventana con el gráfico de
     * JFreechart que representa dichos datos.
     */
    public EjemploJFreeCategoryDataset() {
        // Creamos y rellenamos el modelo de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // Visitas del sitio web 1
        dataset.setValue(100, SITIO_1, "Lunes");
        dataset.setValue(120, SITIO_1, "Martes");
        dataset.setValue(110, SITIO_1, "Miércoles");
        dataset.setValue(103, SITIO_1, "Jueves");
        dataset.setValue(106, SITIO_1, "Viernes");
        // Visitas del sitio web 2
        dataset.setValue(60, SITIO_2, "Lunes");
        dataset.setValue(62, SITIO_2, "Martes");
        dataset.setValue(61, SITIO_2, "Miércoles");
        dataset.setValue(63, SITIO_2, "Jueves");
        dataset.setValue(66, SITIO_2, "Viernes");

        // Salvamos en fichero todos los posibles tipos de graficos
        JFreeChart chart = null;
        for (TiposDeGrafico i : TiposDeGrafico.values()) {
            chart = createChart(dataset, i);
            salvaGraficoEnFichero(chart, i);
        }

        // Hacemos y mostramos una ventana con el último de ellos
        ChartPanel panel = new ChartPanel(chart);

        JFrame ventana = new JFrame("El grafico");
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Crea un JFreeChart a partir del Dataset que se le pasa. El tipo de
     * gráfico es el indicado por TipoDeGrafico, que solo admite tipos de
     * grafico que admitan CategoryDataSet.
     * 
     * @param dataset
     *            Un CategoryDataSet con los datos del grafico
     * @param tipo
     *            El tipo de grafico
     * @return El JFreeChart con el gráfico o null si hay problemas
     */
    private JFreeChart createChart(CategoryDataset dataset, TiposDeGrafico tipo) {
        // Hacemos la ventana con el gráfico
        // Los tres booleans del final:
        // - Si se deben mostrar las leyendas con el nombre de los sitios web
        // - Si se deben mostrar tooltip al poner el ratón sobre las barras
        // - Si se muestran las url
        JFreeChart chart;
        switch (tipo) {
        case AreaChart:
            chart = ChartFactory.createAreaChart("Visitas", "Mes",
                    "Número visitas", dataset, PlotOrientation.VERTICAL, true,
                    true, true);
            break;
        case BarChart:
            chart = ChartFactory.createBarChart("Visitas", "Mes",
                    "Número visitas", dataset, PlotOrientation.VERTICAL, true,
                    true, true);
            break;
        case BarChart3D:
            chart = ChartFactory.createBarChart3D("Visitas", "Mes",
                    "Número visitas", dataset, PlotOrientation.VERTICAL, true,
                    true, true);
            break;
        case LineChart:
            chart = ChartFactory.createLineChart("Visitas", "Mes",
                    "Número visitas", dataset, PlotOrientation.VERTICAL, true,
                    true, true);
            break;
        case LineChart3D:
            chart = ChartFactory.createLineChart3D("Visitas", "Mes",
                    "Número visitas", dataset, PlotOrientation.VERTICAL, true,
                    true, true);
            break;
        case MultiplePieChart:
            chart = ChartFactory.createMultiplePieChart("Visitas", dataset,
                    TableOrder.BY_COLUMN, true, true, true);
            break;
        case MultiplePieChart3D:
            chart = ChartFactory.createMultiplePieChart3D("Visitas", dataset,
                    TableOrder.BY_COLUMN, true, true, true);
            break;
        default:
            chart = null;
        }
        return chart;
    }

    /**
     * Salva el gráfico en un fichero jpg en el directorio temporal por defecto,
     * con el nombre BARCHART_JPG
     * 
     * @param chart
     *            El gráfico JFreechart
     * @param tipo
     */
    private void salvaGraficoEnFichero(JFreeChart chart, TiposDeGrafico tipo) {
        try {
            String tmpDir = System.getProperty("java.io.tmpdir");
            ChartUtilities.saveChartAsJPEG(new File(tmpDir + tipo.toString()
                    + BARCHART_JPG), chart, 500, 300);
            System.out.println("Salvada imagen en " + tmpDir + tipo.toString()
                    + BARCHART_JPG);
        } catch (IOException e) {
            System.err.println("Error creando grafico.");
        }
    }
}

enum TiposDeGrafico {
    AreaChart, BarChart, BarChart3D, LineChart, LineChart3D, MultiplePieChart, MultiplePieChart3D
}