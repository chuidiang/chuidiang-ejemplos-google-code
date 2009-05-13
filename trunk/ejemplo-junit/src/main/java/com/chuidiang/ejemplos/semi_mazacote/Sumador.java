package com.chuidiang.ejemplos.semi_mazacote;

/**
 * Clase encargada de hacer las sumas. Recibe una interface de la que leer
 * líneas de texto con los números que debe sumar, hace la suma, y muestra el
 * resultado a través de otra interface.
 * 
 * @author Chuidiang
 * 
 */
public class Sumador {
    /** Sitio del que leer las líneas de entrada */
    private IfzScanner scanner;

    /** Sitio por el que mostrar los resultados */
    private IfzMuestraResultados muestraResultados;

    /**
     * Lee los sumandos, realiza la suma, y la muestra, siempre usando las
     * interfaces
     */
    public void empiezaATrabajar() {
        double sumando1 = getSumando("Sumando 1:");
        double sumando2 = getSumando("Sumando 2:");
        muestraResultados.println("" + sumando1 + "+" + sumando2 + "="
                + (sumando1 + sumando2));
    }

    /**
     * Lectura de una linea de IfzScanner e intento de convertira a double,
     * repitiendo hasta que la operación tiene éxito.
     * 
     * @param textoPeticion
     * @return El double leído
     */
    private double getSumando(String textoPeticion) {
        boolean correcto;
        correcto = false;
        double sumando2 = 0;
        while (!correcto) {
            try {
                muestraResultados.println(textoPeticion);
                sumando2 = Double.parseDouble(scanner.nextLine());
                correcto = true;
            } catch (Exception e) {
                System.err.println("Eso no vale");
            }
        }
        return sumando2;
    }

    /** Se le pasa por donde mostrar los resultdos */
    public void setIfzMuestraResultados(IfzMuestraResultados muestraResultados) {
        this.muestraResultados = muestraResultados;
    }

    /** Se le pasa de dónde leer las entradas */
    public void setIfzScanner(IfzScanner scanner) {
        this.scanner = scanner;
    }

}
